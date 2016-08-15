/**
 * @author Robert Wilk
 * Created on 2/9/2016.
 */
var url = "/pollination/";
var contractDtoList = url + "contracts";
var contract = url + "contract/";
var contacts = url + "contacts/";
var inspections = url + "inspections/";
var orchards = url + "orchards/";
var progress = url + "progress";
var shipments = url + "shipments/";
var createContract = url + "createContract";
var addOrchard = url + "addOrchard";
var addContract = url + "addContract";
var addShipment = url + "addShipment";
var addInspection = url + "addInspection/"
var renew = url + "emptyOrchard/";
var complete = url + "fillOrchard/";

function getEmptyTableHead() {
    var tableHead = $('#t-head');
    tableHead.empty();
    return tableHead;
}

function getEmptyTableBody(id) {
    var tableBody = $(id);
    tableBody.empty();
    return tableBody;
}

function getEmptyFormBody() {
    var formBody = getFormBody();
    formBody.empty();
    return formBody;
}

function getFormBody() {
    return $('#form-body');
}

function getContract(id) {
    var contractUrl = contract + id;
    $.getJSON(contractUrl, function (data) {
        loadContractDetails(data);
    });
}

function getContacts(id) {
    return get(contacts + id, "Orchard's Contacts", loadContactListModal);
}

function getInspections(id) {
    return get(inspections + id, "Orchard's Inspections", loadInspectionListModal);
}

function getShipments() {
    return get(shipments, "Orchard's Shipments", loadShipmentListModal);
}

function getOrchards() {
    return get(orchards, "Orchards", loadOrchardListModal);
}

function get(type, title, func) {
    $('#table-modal-title').text(title);
    $('#t-body').text("Loading...");
    $.getJSON(type, function (data) {
        func(data);
    });
}

function loadContactListModal(data) {
    loadListModal(data, getContactHead, getContactRow);
}

function loadInspectionListModal(data) {
    loadListModal(data, getInspectionHead, getInspectionRow);
}

function loadShipmentListModal(data) {
    loadListModal(data, getShipmentHead, getShipmentRow);
}

function loadOrchardListModal(data) {
    loadListModal(data, getOrchardHead, getOrchardRow);
}

function loadListModal(data, headFunc, rowFunc) {
    getEmptyTableHead().append(headFunc());
    var tableBody = getEmptyTableBody('#t-body');
    $.each(data, function (i, e) {
        tableBody.append(rowFunc(e));
    });
}

function getContactHead() {
    return $('<tr>').append(
        $('<td>').text('Name'),
        $('<td>').text('Email'),
        $('<td>').text('Phone'),
        $('<td>').text('Address')
    );
}

function getContactRow(e) {
    return $('<tr>').append(
        $('<td>').text(e.id),
        $('<td>').text(e.email),
        $('<td>').text(e.phone),
        $('<td>').text(e.address)
    );
}

function getInspectionHead() {
    return $('<tr>').append(
        $('<td>').text('Date'),
        $('<td>').text('Purpose'),
        $('<td>').text('Notes'),
        $('<td>').text('Edit')
    );
}

function getInspectionRow(e) {
    return $('<tr>').append(
        $('<td>').text(e.date),
        $('<td>').text(e.purpose),
        $('<td>').text(e.notes),
        $('<td>').html([
            '<a class="edit ml10" href="#" title="Edit" data-toggle="modal" data-target="#form-modal"' +
            'onclick="loadEditInspectionModal(' + e.id + ', ' + true + ')">',
            '<i class="material-icons bee-board-icon">create</i>',
            '</a>'].join('')
        )
    );
}

function getShipmentHead() {
    return $('<tr>').append(
        $('<td>').text('Name'),
        $('<td>').text('Date'),
        $('<td>').text('Direction'),
        $('<td>').text('Dud'),
        $('<td>').text('From'),
        $('<td>').text('To'),
        $('<td>').text('In'),
        $('<td>').text('Notes')
    );
}

function getShipmentRow(e) {
    return $('<tr>').append(
        $('<td>').text(e.id),
        $('<td>').text(e.date),
        $('<td>').text(e.direction),
        $('<td>').text(e.dud),
        $('<td>').text(e.from),
        $('<td>').text(e.to),
        $('<td>').text(e.in),
        $('<td>').text(e.notes)
    );
}

function getOrchardHead() {
    return $('<tr>').append(
        $('<td>').text('Name'),
        $('<td>').text('Max Hives'),
        $('<td>').text('Status'),
        $('<td>').text('Owner'),
        $('<td>').text('Edit')
    );
}

function getOrchardRow(e) {
    return $('<tr>').append(
        $('<td>').text(e.yardName),
        $('<td>').text(e.maxHives),
        $('<td>').text(e.status),
        $('<td>').text(e.owner.person.name),
        $('<td>').html([
            '<a class="edit ml10" href="#" title="Edit" data-toggle="modal" data-target="#form-modal"' +
            'onclick="loadEditOrchardModal(' + e.id + ', ' + true + ')">',
                '<i class="material-icons bee-board-icon">create</i>',
                '</a>'].join('')
        )
    );
}

function post(url, json, callback) {
    $.ajax({
        type: "POST",
        beforeSend: function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        },
        contentType: "application/json",
        url: url + '/' + JSON.stringify(json) + '/',
        data: json,
        dataType: 'json',
        timeout: 100000,
        success: function (msg) {
            getEmptyFormBody();
            $('#form-modal').modal('hide');
            $("#form").unbind('submit');
            callback();
            console.log("SUCCESS: ", msg);
            return false;
        }
    });
}

function postContract() {
    var json = getSimpleJson($('#form').serializeArray());
    post(addContract, json, function () {
        var id = $('#id');
        if (id.val() != '') {
            $('#id').remove();
            getContract(id.val());
        }
        $('#contract-table').bootstrapTable('refresh');
        loadProgress();
    });
}

function postOrchard(reload) {
    var json = getOrchardJson($('#form').serializeArray());
    post(addOrchard, json, function () {
        var id = $('#id');
        if (id.val() != '') {
            $('#id').remove();
            getContract(id.val());
        }
        if (reload)
            getOrchards();
        $('#contract-table').bootstrapTable('refresh');
        loadProgress();
    });
}

function postShipment() {
    var json = getSimpleJson($('#form').serializeArray());
    post(addShipment, json, function () {
        $('#id').remove();
    });
}

function postInspection(reload) {
    var json = getSimpleJson($('#form').serializeArray());
    post(addInspection, json, function () {
        if (reload) {
            getInspections(json.orchard);
        }
    });
}

function emptyOrchard(contractId) {
    $.ajax(renew + contractId).done( function() {
        $('#contract-table').bootstrapTable('refresh');
        getContract(contractId);
        loadProgress();
    });
}

function fillOrchard(contractId) {
    $.ajax(complete + contractId).done( function() {
        $('#contract-table').bootstrapTable('refresh');
        getContract(contractId);
        loadProgress();
    });
}

function getSimpleJson(form) {
    var json = {};
    $.each(form, function () {
        json[this.name] = this.value || '';
    });
    return json;
}

function getOrchardJson(form) {
    var json = {};
    json['address'] = {};
    json['contacts'] = {};
    $.each(form, function () {
        switch (this.name) {
            case 'street':
            case 'suite':
            case 'city':
            case 'state':
            case 'zip':
                json['address'][this.name] = this.value || '';
                break;
            default:
                json[this.name] = this.value || '';
        }
    });
    return json;
}

function getBaseSelector(name) {
    return $('<select>')
        .attr('class', 'form-control')
        .attr('name', name)
        .attr('id', name);
}

function getSelector(data, name) {
    var select = getBaseSelector(name);
    $.each(data, function (i, e) {
        select.append(
            $('<option>')
                .attr('id', e)
                .attr('value', e)
                .text(e)
        )
    });
    return select;
}

function getSelectorWithName(data, name) {
    var select = getBaseSelector(name);
    $.each(data, function (i, e) {
        select.append(
            $('<option>')
                .attr('id', e.name)
                .attr('value', e.id)
                .text(e.name)
        )
    });
    return select;
}

function getTabAnchor(id, text) {
    return $('<a>')
        .attr('href', '#' + id)
        .attr('data-toggle', 'tab')
        .text(text);
}

function getTabPane(id, active) {
    var c = 'pane tab-pane';
    if (active)
        c += ' active';
    return $('<div>')
        .attr('class', c)
        .attr('id', id);
}

function getSubmitButton(text) {
    return getGroupDiv()
        .append(
            $('<div>')
                .attr('class', 'col-sm-4 control-label')
                .append(
                    $('<button>')
                        .attr('class', 'btn btn-primary')
                        .attr('type', 'submit')
                        .text(text)
                )
        )
}

function getFormGroup(for_id, label, type, val) {
    return getGroupDiv()
        .append(
            getFormGroupLabel(for_id, label),
            getFormGroupInput(for_id, type, val)
        );
}

function getMapFormGroup(for_id) {
    return getGroupDiv()
        .append(
            $('<div>')
                .attr('class', 'col-sm-1'),
            $('<div>')
                .attr('class', 'col-sm-10')
                .attr('id', for_id)
                .attr('style', 'height:300px'),
            $('<div>')
                .attr('class', 'col-sm-1')
        )
}

function getGroupDiv() {
    return $('<div>')
        .attr('class', 'form-group');
}

function getFormGroupLabel(for_id, label) {
    return $('<label>')
        .attr('class', 'col-sm-2 control-label')
        .attr('for', for_id)
        .text(label);
}

function getFormGroupInput(for_id, type, val) {
    return $('<div>')
        .attr('class', 'col-sm-10')
        .append(
            $('<input>')
                .attr('class', 'form-control')
                .attr('id', for_id)
                .attr('name', for_id)
                .attr('type', type)
                .val(val)
        );
}

function getFormGroupSelector(data) {
    return $('<div>')
        .attr('class', 'col-sm-10')
        .append(data);
}

function getFormGroupWithSelector(for_id, label, selector) {
    return getGroupDiv()
        .append(
            getFormGroupLabel(for_id, label),
            getFormGroupSelector(selector)
        );
}

function getHiddenIdInput(id) {
    return getFormGroupInput("id", "hidden", id);
}

function selectOption(id) {
    $('#' + id).prop('selected', true);
}

/**
 * Functions that return a built for for the corresponding types.
 */
function getShipmentForm(data, action) {
    var form = $('#form');
    form.attr('action', action);
    form.submit(function (event) {
        event.preventDefault();
        postShipment();
        return false;
    });
    getEmptyFormBody()
        .append(
            getFormGroupWithSelector('direction', 'Direction', getSelector(data.directions, 'direction')),
            getFormGroup('date', 'Date', 'date'),
            getFormGroup('to', 'To', 'text'),
            getFormGroup('from', 'From', 'text'),
            getFormGroup('in', 'In', 'text'),
            getFormGroup('dud', 'Dud', 'text'),
            getFormGroup('notes', 'Notes', 'text')
        );
}

function fillShipmentForm(data) {
    $('#form').append(getHiddenIdInput(data.id));
    putInputValue('date', data.date);
    putInputValue('to', data.to);
    putInputValue('from', data.from);
    putInputValue('in', data.in);
    putInputValue('dud', data.dud);
    putInputValue('notes', data.notes);
    selectOption(data.direction, data.direction);
}

function createShipmentForm(data) {
    $('#form-modal-title').text("Create Shipment");
    getShipmentForm(data, '/pollination/addShipment');
    getFormBody().append(getSubmitButton('Create'));
}

function editShipmentForm(data) {
    $('#form-modal-title').text("Edit Shipment");
    getShipmentForm(data.polliShipmentCreateDTO, '/pollination/addShipment');
    getFormBody().append(getSubmitButton('Save'));
    fillShipmentForm(data.shipment);
}

function getContractForm(data, action) {
    var form = $('#form');
    form.attr('action', action);
    form.submit(function (event) {
        event.preventDefault();
        postContract();
        return false;
    });
    getEmptyFormBody()
        .append(
            getFormGroupWithSelector('orchard', "Orchard", getSelector(data.orchards, 'orchard')),
            getFormGroup('amount', 'Amount', 'text'),
            getFormGroupWithSelector('broker', "Broker", getSelectorWithName(data.people, 'broker')),
            getFormGroup('inDate', 'Move-in Date', 'date'),
            getFormGroup('outDate', 'Move-out Date', 'date')
        );
}

function fillContractForm(data) {
    $('#form').append(getHiddenIdInput(data.id));
    putInputValue('amount', data.amount);
    putInputValue('inDate', data.moveInDate);
    putInputValue('outDate', data.moveOutDate);
    selectOption(data.orchard.yardName);
    selectOption(data.broker.name);
}

function createContractForm(data) {
    $('#form-modal-title').text("Create Contract");
    getContractForm(data, '/pollination/addContract');
    getFormBody().append(getSubmitButton('Create'));
}

function editContractForm(data) {
    $('#form-modal-title').text("Edit Contract");
    getContractForm(data.contractCreateDTO, '/pollination/addContract');
    getFormBody().append(getSubmitButton('Save'));
    fillContractForm(data.contract);
}

function getOrchardForm(data, action, reload) {
    var form = $('#form');
    form.attr('action', action);
    form.submit(function (event) {
        event.preventDefault();
        postOrchard(reload);
        return false;
    });
    getEmptyFormBody()
        .append(
            $('<ul>')
                .attr('class', 'nav nav-tabs')
                .attr('id', 'tabContent')
                .append(
                    $('<li>')
                        .attr('class', 'active')
                        .append(getTabAnchor('details', 'Details')),
                    $('<li>')
                        .append(getTabAnchor('location', 'Location')),
                    $('<li>')
                        .append(getTabAnchor('access', 'Access'))
                ),
            $('<div>')
                .attr('class', 'tab-content')
                .append(
                    getTabPane('details', true)
                        .append(
                            getFormGroup('yardName', 'Name', 'text'),
                            getFormGroup('maxHives', 'Max Hives', 'text'),
                            getFormGroupWithSelector('status', 'Status', getSelector(data.stati, 'status')),
                            getFormGroupWithSelector('owner', 'Owner', getSelectorWithName(data.people, 'owner')),
                            getFormGroupWithSelector('rentReceiver', 'Rent Receiver', getSelectorWithName(data.people, 'rentReceiver'))
                        ),
                    getTabPane('location', false)
                        .append(
                            getFormGroup('street', 'Street', 'text'),
                            getFormGroup('suite', 'Suite', 'text'),
                            getFormGroup('city', 'City', 'text'),
                            getFormGroup('state', 'State', 'text'),
                            getFormGroup('zip', 'Zip', 'text'),
                            getFormGroupWithSelector('region', 'Region', getSelector(data.regions, 'region')),
                            getFormGroup('longitude', 'Longitude', 'text'),
                            getFormGroup('latitude', 'Latitude', 'text'),
                            getMapFormGroup('map-div-modal')
                        ),
                    getTabPane('access', false)
                        .append(
                            getFormGroup('combo', 'Combination or Key', 'text'),
                            getFormGroup('accessNotes', 'Access Notes', 'text')
                        )
                )
        );
    getLocation();  //sets location of map
    //resizes and recenters maps whenever modal is opened
    $('#tabContent').on('shown.bs.tab', function (e) {
        var curCenter = map.getCenter();
        google.maps.event.trigger(map, 'resize');
        map.setCenter(curCenter);
    });
}

function fillOrchardForm(data) {
    $('#form').append(getHiddenIdInput(data.id));
    putInputValue('yardName', data.yardName);
    putInputValue('maxHives', data.maxHives);
    putInputValue('street', data.address.street);
    putInputValue('suite', data.address.suite);
    putInputValue('city', data.address.city);
    putInputValue('state', data.address.state);
    putInputValue('zip', data.address.zip);
    putInputValue('longitude', data.longitude);
    putInputValue('latitude', data.latitude);
    putInputValue('combo', data.combo);
    putInputValue('accessNotes', data.accessNotes);
    selectOption(data.status);
    selectOption(data.owner.person.name);
    selectOption(data.rentReceiver.name);
    selectOption(data.region.name);
}

function putInputValue(name, value) {
    $('#' + name).val(value == null ? '' : value);
}

function createOrchardForm(data) {
    $('#form-modal-title').text("Create Orchard");
    getOrchardForm(data, '/pollination/addOrchard');
    getFormBody().append(getSubmitButton('Create'));
}

function editOrchardForm(data, reload) {
    $('#form-modal-title').text("Edit Orchard");
    getOrchardForm(data.orchardCreateDTO, '/pollination/addOrchard', reload);
    getFormBody().append(getSubmitButton('Save'));
    fillOrchardForm(data.orchard);
}

function createInspectionForm(data) {
    $('#form-modal-title').text("Create Inspection");
    getInspectionForm(data, '/pollination/addInspection');
    getFormBody().append(getSubmitButton('Create'));
}

function editInspectionForm(data, reload) {
    $('#form-modal-title').text("Edit Inspection");
    getInspectionForm(data, '/pollination/addInspection', reload);
    getFormBody().append(getSubmitButton('Save'));
    fillInspectionForm(data);
}

function getInspectionForm(data, action, reload) {
    var form = $('#form');
    form.attr('action', action);
    form.submit(function (event) {
        event.preventDefault();
        postInspection(reload);
        return false;
    });
    getEmptyFormBody()
        .append(
            getFormGroupWithSelector('orchard', "Orchard", getSelector(data.picDto.orchards, 'orchard')),
            getFormGroup('date', 'Date', 'date'),
            getFormGroupWithSelector('purpose', "Purpose", getSelector(data.picDto.purposes, 'purpose')),
            getFormGroup('notes', 'Notes', 'text')
        );
}

function fillInspectionForm(data) {
    $('#form').append(getHiddenIdInput(data.inspection.id));
    putInputValue('date', data.inspection.date);
    putInputValue('notes', data.notes);
    selectOption(data.inspection.orchard.yardName);
    selectOption(data.purpose);
}
// End form functions

/**
 * Functions that get json from the server and builds the form
 */
function loadCreateOrchardModal() {
    $.getJSON("/pollination/createOrchard", function (data) {
        createOrchardForm(data);
    });
}

function loadCreateContractModal() {
    $.getJSON("/pollination/createContract", function (data) {
        createContractForm(data);
    });
}

function loadCreateInspectionModal() {
    $.getJSON("/pollination/createInspection", function (data) {
        createInspectionForm(data);
    });
}

function loadEditContractOrchardModal(id, reload) {
    $.getJSON("/pollination/editContractOrchard/" + id, function (data) {
        editOrchardForm(data, reload);
    });
}

function loadEditOrchardModal(id, reload) {
    $.getJSON("/pollination/editOrchard/" + id, function (data) {
        editOrchardForm(data, reload);
    });
}

function loadEditContractModal(id) {
    $.getJSON("/pollination/editContract/" + id, function (data) {
        editContractForm(data);
    });
}

function loadCreateShipmentModal() {
    $.getJSON("/pollination/createShipment", function (data) {
        createShipmentForm(data);
    });
}

function loadEditShipmentModal(id) {
    $.getJSON("/pollination/editShipment/" + id, function (data) {
        editShipmentForm(data);
    });
}

function loadEditInspectionModal(id, reload) {
    $.getJSON("/pollination/editInspection/" + id, function (data) {
        editInspectionForm(data, reload);
    });
}
// End form loaders

function loadContractDetails(data) {
    $('#id').html('<b>Contract Id: </b>' + data.id);
    $('#orchard').html('<b>Orchard: </b>' + data.orchard.yardName);
    $('#amount').html('<b>Amount: </b>' + data.amount);
    $('#in').html('<b>In Date: </b>' + data.moveInDate);
    $('#out').html('<b>Out Date: </b>' + data.moveOutDate);
    $('#broker').html('<b>Broker: </b>' + (data.broker == null ? '' : data.broker.name));
    $('#number').html('<b>Phone: </b>' + (data.broker == null ? '' : data.broker.contactInfo == null ? '' : data.broker.contactInfo.phone));
    $('#edit').html('<a title="Edit" href="#"><i class="material-icons md-24 bee-board-icon" data-toggle="modal" ' +
        'data-target="#form-modal" onclick="loadEditContractModal(' + data.id + ')">create</i></a>');
    $('#complete').html('<a title="Fill" href="#" onclick="fillOrchard(' + data.id + ')"><i class="material-icons md-24 bee-board-icon">check</i></a>');
    $('#renew').html('<a title="Empty" href="#" onclick="emptyOrchard(' + data.id + ')"><i class="material-icons md-24 bee-board-icon">autorenew</i></a>');
    $('#inspections').html('<a title="Inspections" href="#"><i class="material-icons md-24 bee-board-icon" data-toggle="modal" ' +
        'data-target="#table-modal" onclick="getInspections(' + data.id + ')">visibility</i></a>');
    var a = data.amount;
    var c = data.orchard.count;
    var p = (c / a) * 100;
    $('#progress').html('<b>% Fulfilled:</b><br/><div class="progress"><div class="progress-bar" role="progressbar" ' +
        'aria-valuemin="0" aria-valuemax="100" aria-valuenow="' + p + '" style="width: ' + p + '%"></div></div>'
    );
}

function progressFormatter(value, row, index) {
    return [
        '<div class="progress"><div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100"' +
        'aria-valuenow="' + value + '" style="width: ' + value + '%;" ></div></div>'
    ].join('');
}

function tableRowClick(e, row, $element) {
    getContract(row["id"]);
    if (row["latitude"] == null || row["longitude"] == null) {
        console.log("Longitude or Latitude is null!");
    }
    var lat = row["latitude"];
    var long = row["longitude"];
    var labels = '<div><h5>' + row["orchardName"] + '</h5></div>';
    //sets location of map on first drop location.
    initialize(long, lat);
    //Creates Markers for map
    createMarkers(long, lat, labels);
    if (document.getElementById('map-div').style.display == 'none')
        document.getElementById('map-div').style.display = 'block';
}

function loadProgress() {
    $.getJSON(progress, function (data) {
        $('#current-count').text('Current count: ' + data.fulfilled);
        $('#contracted-count').text('Contracted count: ' + data.needed);
        var p = data.progress * 100;
        $('#progress-bar').css('width', p + '%').attr('aria-valuenow', p);
    })
}

window.operateEvents = {
    'click .edit': function (e, value, row, index) {
        // location.href = "/yard/update/" + row["id"];
    },
    'click .remove': function (e, value, row, index) {
        // location.href = "/yard/delete/" + row["id"];
    }
};

$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
    loadProgress();
});

var x = document.getElementById("error");
var map;
var marker;
var prevMarker;
var coords;
var lat;
var lng;
var markerImage;
<!-- Query Device for Location -->
function getLocation() {

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(setVals, showError);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
    markerImage = {
        url: 'http://i.imgur.com/ALU8OuA.png',
        size: new google.maps.Size(45, 45),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(23, 45)
    };
}
<!-- Sets Latitude and Longitude values -->
function setVals(position) {
    lat = position.coords.latitude;
    lng = position.coords.longitude;
    document.getElementById("latitude").value = lat;
    document.getElementById("longitude").value = lng;
    coords = new google.maps.LatLng(lat, lng);
    var mapOptions = {
        zoom: 15,
        center: coords,
        mapTypeControl: true,
        mapTypeId: google.maps.MapTypeId.HYBRID
    };
    map = new google.maps.Map(
        document.getElementById("map-div-modal"), mapOptions
    );
    google.maps.event.addListener(map, 'click', function (event) {
        placeMarker(event.latLng);
    });

    marker = new google.maps.Marker({
        position: coords,
        map: map,
        icon: markerImage,
        title: "Current Location"
    });
}
function placeMarker(location) {
    lat = location.lat();
    lng = location.lng();
    setMarkerPosition(marker, lat, lng);
    document.getElementById("latitude").value = location.lat();
    document.getElementById("longitude").value = location.lng();
}
function showError(error) {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            x.innerHTML = "User denied the request for Geolocation.";
            break;
        case error.POSITION_UNAVAILABLE:
            x.innerHTML = "Location information is unavailable.";
            break;
        case error.TIMEOUT:
            x.innerHTML = "The request to get user location timed out.";
            break;
        case error.UNKNOWN_ERROR:
            x.innerHTML = "An unknown error occurred.";
            break;
    }
}
<!-- This moves the marker on the map when you click -->
function setMarkerPosition(marker, lat, lng) {
    marker.setPosition(
        new google.maps.LatLng(
            lat,
            lng)
    );
}