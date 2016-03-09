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
    var formBody = $('#form-body');
    formBody.empty();
    return formBody;
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
    // TODO add html for shipment row.
}

function getInspectionHead() {
    return $('<tr>').append(
        $('<td>').text('Name'),
        $('<td>').text('Email'),
        $('<td>').text('Phone')
    );
}

function initContractTable() {
    // Load header
    loadContractTable();
}

function getContractTableHead() {

}

function getContractTableBody(data) {

}

function getInspectionRow(e) {
    //TODO add html for inspection head.
}

function getStatusSelector(data) {
    var select = $('<select>')
        .attr('class', 'form-control');
    $.each(data, function (i, e) {
        select.append(
            $('<option>')
                .text(e)
        )
    });
    return select;
}

function getPersonSelector(data) {
    var select = $('<select>')
        .attr('class', 'form-control');
    $.each(data, function (i, e) {
        select.append(
            $('<option>')
                .text(e.name)
        )
    });
    return select;
}

function getRegionSelector(data) {
    var select = $('<select>')
        .attr('class', 'form-control');
    $.each(data, function (i, e) {
        select.append(
            $('<option>')
                .text(e)
        )
    });
    return select;
}

function getOrchardSelector(data) {
    var select = $('<select>')
        .attr('class', 'form-control');
    $.each(data, function (i, e) {
        select.append(
            $('<option>')
                .text(e)
        )
    });
    return select;
}

function createOrchardForm(data) {
    $('#form-modal-title').text("Create Orchard");
    var body = getEmptyFormBody();
    var stati = getStatusSelector(data.stati);
    var owners = getPersonSelector(data.people);
    var rentees = getPersonSelector(data.people);
    var regions = getRegionSelector(data.regions);
    body.append(
        $('<ul>')
            .attr('class', 'nav nav-tabs')
            .attr('id', 'tabContent')
            .append(
                $('<li>')
                    .attr('class', 'active')
                    .append(
                        $('<a>')
                            .attr('href', '#details')
                            .attr('data-toggle', 'tab')
                            .text('Details')
                    ),
                $('<li>')
                    .append(
                        $('<a>')
                            .attr('href', '#location')
                            .attr('data-toggle', 'tab')
                            .text('Location')
                    ),
                $('<li>')
                    .append(
                        $('<a>')
                            .attr('href', '#access')
                            .attr('data-toggle', 'tab')
                            .text('Access')
                    )
            ),
        $('<div>')
            .attr('class', 'tab-content')
            .append(
                $('<div>')
                    .attr('class', 'pane tab-pane active')
                    .attr('id', 'details')
                    .append(
                        getFormGroup('name', 'Name', 'text'),
                        getFormGroup('maxHives', 'Max Hives', 'text'),
                        getFormGroupWithSelector('status', 'Status', stati),
                        getFormGroupWithSelector('owner', 'Owner', owners),
                        getFormGroupWithSelector('rentReceiver', 'Rent Receiver', rentees)
                    ),
                $('<div>')
                    .attr('class', 'pane tab-pane')
                    .attr('id', 'location')
                    .append(
                        getFormGroup('street', 'Street', 'text'),
                        getFormGroup('suite', 'Suite', 'text'),
                        getFormGroup('city', 'City', 'text'),
                        getFormGroup('state', 'State', 'text'),
                        getFormGroup('zip', 'Zip', 'text'),
                        getFormGroupWithSelector('region', 'Region', regions),
                        getFormGroup('longitude', 'Longitude', 'text'),
                        getFormGroup('latitude', 'Latitude', 'text')
                    ),
                $('<div>')
                    .attr('class', 'pane tab-pane')
                    .attr('id', 'access')
                    .append(
                        getFormGroup('combo', 'Combination or Key', 'text'),
                        getFormGroup('accessNotes', 'Access Notes', 'text')
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<div>')
                    .attr('class', 'col-sm-4 control-label')
                    .append(
                        $('<button>')
                            .attr('class', 'btn btn-primary')
                            .attr('type', 'submit')
                            .text('Create')
                    )
            )
    );
}

function getFormGroup(for_id, label, type) {
    return getGroupDiv()
        .append(
            getFormGroupLabel(for_id, label),
            getFormGroupInput(for_id, type)
        )
        ;
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

function getFormGroupInput(for_id, type) {
    return $('<div>')
        .attr('class', 'col-sm-10')
        .append(
            $('<input>')
                .attr('class', 'form-control')
                .attr('id', for_id)
                .attr('type', type)
        )
    ;
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
        )
        ;
}

function createContractForm(data) {
    $('#form-modal-title').text("Create Contract");
    var body = getEmptyFormBody();
    var people = getPersonSelector(data.people);
    var orchards = getOrchardSelector(data.orchards);
    body.append(
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<label>')
                    .attr('class', 'col-sm-2 control-label')
                    .attr('for', 'orchard')
                    .text("Orchard"),
                $('<div>')
                    .attr('class', 'col-sm-10')
                    .append(
                        orchards
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<label>')
                    .attr('class', 'col-sm-2 control-label')
                    .attr('for', 'amount')
                    .text("Amount"),
                $('<div>')
                    .attr('class', 'col-sm-10')
                    .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'amount')
                            .attr('type', 'text')
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<label>')
                    .attr('class', 'col-sm-2 control-label')
                    .attr('for', 'broker')
                    .text("Broker"),
                $('<div>')
                    .attr('class', 'col-sm-10')
                    .append(
                        people
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<label>')
                    .attr('class', 'col-sm-2 control-label')
                    .attr('for', 'inDate')
                    .text("Move-in Date"),
                $('<div>')
                    .attr('class', 'col-sm-10')
                    .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'inDate')
                            .attr('type', 'date')
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<label>')
                    .attr('class', 'col-sm-2 control-label')
                    .attr('for', 'inDate')
                    .text("Move-out Date"),
                $('<div>')
                    .attr('class', 'col-sm-10')
                    .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'outDate')
                            .attr('type', 'date')
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<div>')
                    .attr('class', 'col-sm-offset-2 col-sm-10')
                    .append(
                        $('<button>')
                            .attr('type', 'submit')
                            .attr('class', 'btn btn-primary')
                            .text('Create')
                    )
            )
    );
}

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

function loadCreateShipmentModal() {
    $('#form-modal-title').text("Create Shipment");
    var body = getEmptyFormBody();
    body.append(
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<label>')
                    .attr('class', 'col-sm-2 control-label')
                    .attr('for', 'direction')
                    .text("Direction"),
                $('<div>')
                    .attr('class', 'col-sm-10')
                    .append(
                        $('<select>')
                            .attr('class', 'form-control')
                            .attr('id', 'direction')
                            .append(
                                $('<option>')
                                    .text("IN"),
                                $('<option>')
                                    .text("OUT")
                            )
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<label>')
                    .attr('class', 'col-sm-2 control-label')
                    .attr('for', 'in')
                    .text("In"),
                $('<div>')
                    .attr('class', 'col-sm-10')
                    .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'in')
                            .attr('type', 'text')
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<label>')
                    .attr('class', 'col-sm-2 control-label')
                    .attr('for', 'date')
                    .text("Date"),
                $('<div>')
                    .attr('class', 'col-sm-10')
                    .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'date')
                            .attr('type', 'date')
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<label>')
                    .attr('class', 'col-sm-2 control-label')
                    .attr('for', 'dud')
                    .text("Dud"),
                $('<div>')
                    .attr('class', 'col-sm-10')
                    .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'dud')
                            .attr('type', 'text')
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<label>')
                    .attr('class', 'col-sm-2 control-label')
                    .attr('for', 'notes')
                    .text("Notes"),
                $('<div>')
                    .attr('class', 'col-sm-10')
                    .append(
                        $('<textarea>')
                            .attr('class', 'form-control')
                            .attr('width', '100%')
                            .attr('rows', '5')
                            .attr('id', 'notes')
                            .attr('type', 'text')
                    )
            ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
                $('<div>')
                    .attr('class', 'col-sm-offset-2 col-sm-10')
                    .append(
                        $('<button>')
                            .attr('type', 'submit')
                            .attr('class', 'btn btn-primary')
                            .text('Create')
                    )
            )
    );
}

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

function getHiveCount() {
    $.ajax("/pollination/hiveCounts", function (data) {

    });
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
        location.href = "/yard/update/" + row["id"];
    },
    'click .remove': function (e, value, row, index) {
        location.href = "/yard/delete/" + row["id"];
    }
};

$(document).ready(function () {
    loadContractTable();
    $('[data-toggle="tooltip"]').tooltip();
});