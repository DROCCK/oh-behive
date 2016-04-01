/**
 * @author Robert Wilk
 * Created on 2/9/2016.
 */
var url = "/pollination/";
var contractDtoList = url + "contracts";
var contract = url + "contract/";
var contacts = url + "contacts/";
var inspections = url + "inspections/";
var shipments = url + "shipments/";
var createContract = url + "createContract";
var addOrchard = url + "addOrchard";
var addContract = url + "addContract";
var addShipment = url + "addShipment";

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

function getShipments(id) {
    return get(shipments + id, "Orchard's Shipments", loadShipmentListModal);
}

function get(type, title, func) {
    $('#table-modal-title').text(title);
    $('#t-body').text("Loading...");
    $.getJSON(type, function (data) {
        func(data);
    });
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
        $('#contract-table').bootstrapTable('refresh');
    });
}

function postOrchard() {
    var json = getOrchardJson($('#form').serializeArray());
    alert(JSON.stringify(json));
    post(addOrchard, json, function () { });
}

function postShipment() {
    var json = getSimpleJson($('#form').serializeArray());
    post(addShipment, json, function () { });
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
    alert(JSON.stringify(form));
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

function loadListModal(data, headFunc, rowFunc) {
    getEmptyTableHead().append(headFunc());
    var tableBody = getEmptyTableBody('#t-body');
    $.each(data, function (i, e) {
        tableBody.append(rowFunc(e));
    });
}

function loadContactListModal(data) {
    loadListModal(data, getContactHead, getContactRow);
}

function loadShipmentListModal(data) {
    loadListModal(data, getShipmentHead, getShipmentRow);
}

function loadInspectionListModal(data) {
    loadListModal(data, getInspectionHead, getInspectionRow);
}

function getContactHead() {
    return $('<tr>').append(
        $('<td>').text('Name'),
        $('<td>').text('Email'),
        $('<td>').text('Phone')
    );
}

function getContactRow(e) {
    return $('<tr>').append(
        $('<td>').text(e.name),
        $('<td>').text(e.contactInfo.email),
        $('<td>').text(e.contactInfo.phone)
    );
}

function getShipmentHead() {
    return $('<tr>').append(
        $('<td>').text('Name'),
        $('<td>').text('Email'),
        $('<td>').text('Phone')
    );
}

function getShipmentRow(e) {
    return $('<tr>').append(
        $('<td>').text(e.name),
        $('<td>').text(e.contactInfo.email),
        $('<td>').text(e.contactInfo.phone)
    );
}

function getInspectionHead() {
    return $('<tr>').append(
        $('<td>').text('Name'),
        $('<td>').text('Email'),
        $('<td>').text('Phone')
    );
}

function getInspectionRow(e) {
    return $('<tr>').append(
        $('<td>').text(e.name),
        $('<td>').text(e.contactInfo.email),
        $('<td>').text(e.contactInfo.phone)
    );
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
    getFormGroupInput(for_id, "hidden", id);
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
    });
    getEmptyFormBody()
        .append(
            getFormGroupWithSelector('direction', 'Direction', getSelector(data.directions, 'direction')),
            getFormGroup('date', 'Date', 'date'),
            getFormGroup('to', 'To', 'text'),
            getFormGroup('from', 'From', 'text'),
            getFormGroup('in', 'In', 'text'),
            getFormGroup('dud', 'Dud', 'text'),
            getFormGroup('notes', 'Notes', 'text'),
            getSubmitButton('Create')
        );
}

function fillShipmentForm(data) {
    // Add input for direction selection
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
}

function editShipmentForm(data) {
    $('#form-modal-title').text("Edit Shipment");
    getShipmentForm(data.polliShipmentCreateDTO, '/pollination/editShipment');
    fillShipmentForm(data.shipment);

}

function getContractForm(data, action) {
    var form = $('#form');
    form.attr('action', action);
    form.submit(function (event) {
        event.preventDefault();
        postContract();
    });
    getEmptyFormBody()
        .append(
            getFormGroupWithSelector('orchard', "Orchard", getSelector(data.orchards, 'orchard')),
            getFormGroup('amount', 'Amount', 'text'),
            getFormGroupWithSelector('broker', "Broker", getSelectorWithName(data.people, 'broker')),
            getFormGroup('inDate', 'Move-in Date', 'date'),
            getFormGroup('outDate', 'Move-out Date', 'date'),
            getSubmitButton('Create')
        );
}

function fillContractForm(data) {
    // Add values to orchard and broker selectors
    putInputValue('amount', data.amount);
    putInputValue('inDate', data.moveInDate);
    putInputValue('outDate', data.moveOutDate);
    selectOption(data.orchard.yardName);
    selectOption(data.broker.name);
}

function createContractForm(data) {
    $('#form-modal-title').text("Create Contract");
    getContractForm(data, '/pollination/addContract');
}

function editContractForm(data) {
    $('#form-modal-title').text("Edit Contract");
    getContractForm(data.contractCreateDTO, '/pollination/editContract');
    fillContractForm(data.contract);
}

function getOrchardForm(data, action) {
    var form = $('#form');
    form.attr('action', action);
    form.submit(function (event) {
        event.preventDefault();
        postOrchard();
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
                            getFormGroup('latitude', 'Latitude', 'text')
                        ),
                    getTabPane('access', false)
                        .append(
                            getFormGroup('combo', 'Combination or Key', 'text'),
                            getFormGroup('accessNotes', 'Access Notes', 'text')
                        )
                ),
            getSubmitButton('Create')
        );
}

function fillOrchardForm(data) {
    putInputValue('yardName', data.yardName);
    putInputValue('maxHives', data.maxHives);
    // Add drop-down vals for owner and rent receiver.
    putInputValue('street', data.address.street);
    putInputValue('suite', data.address.suite);
    putInputValue('city', data.address.city);
    putInputValue('state', data.address.state);
    putInputValue('zip', data.address.zip);
    putInputValue('longitude', data.longitude);
    putInputValue('latitude', data.latitude);
    putInputValue('combo', data.combo);
    putInputValue('accessNotes', data.accessNotes);
}

function putInputValue(name, value) {
    $('#' + name).val(value == null ? '' : value);
}

function createOrchardForm(data) {
    $('#form-modal-title').text("Create Orchard");
    getOrchardForm(data, '/pollination/addOrchard');

}

function editOrchardForm(data) {
    $('#form-modal-title').text("Edit Orchard");
    getOrchardForm(data.orchardCreateDTO, '/pollination/addOrchard');
    fillOrchardForm(data.orchard);
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

function loadEditOrchardModal(id) {
    $.getJSON("/pollination/editOrchard/" + id, function (data) {
        editOrchardForm(data);
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
// End form loaders

function loadContractDetails(data) {
    $('#id').html('Contract Id: ' + data.id);
    //$('#orchard').html('<b>' + data.orchard.yardName + '</b>');
    $('#amount').html('Amount: ' + data.amount);
    $('#in').html('In Date: ' + data.moveInDate);
    $('#out').html('Out Date: ' + data.moveOutDate);
    $('#broker').html('Broker: ' + data.broker.name);
    $('#number').html('Phone: ' + data.broker.contactInfo.phone);
    $('#edit').html('<a href="#"><i class="material-icons md-24 bee-board-icon">create</i></a>');
    $('#delete').html('<a href="#"><i class="material-icons md-24 bee-board-icon">delete</i></a>');
    $('#contacts').html('<a href=#><i class="material-icons md-24 bee-board-icon" data-toggle="modal" ' +
        'data-target="#table-modal" onclick="getContacts(0)">person_outline</i></a>');
    $('#shipments').html('<a href="#"><i class="material-icons md-24 bee-board-icon" data-toggle="modal" ' +
        'data-target="#table-modal" onclick="loadShipmentListModal()">visibility</i></a>');
    $('#progress').html('% Fulfilled:<br/><div class="progress"><div class="progress-bar" role="progressbar" ' +
        'aria-valuemin="0" aria-valuemax="100" aria-valuenow="' + data.count + '" style="width: ' + data.count + '%"></div></div>'
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
    var lat = 48.8582;
    var long = 2.2945;
    var labels = '<div><p>Hello Polli</p></div>';
    //sets location of map on first drop location.
    initialize(long, lat);
    //Creates Markers for map
    createMarkers(long, lat, labels);
    if (document.getElementById('map-div').style.display == 'none')
        document.getElementById('map-div').style.display = 'block';
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
});