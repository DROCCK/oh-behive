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

function loadTableData(data) {
    alert(data);
    // Load full contract into table row here.
}

function loadContractTable() {
    $.getJSON(contractDtoList, function (data) {
        var body = getEmptyTableBody('#contract-table-body');
        $.each(data, function (i, e) {
            body.append(loadContractRow(e));
            body.append(addExpandedRow(e));
        });
    });
}

function addExpandedRow(e) {
    return $('<tr>')
        .attr('class', 'collapse')
        .attr('id', 'expanded-row-' + e.id)
        .attr('onclick', '')
        .append(
            $('<td>')
                .attr('colSpan', 4)
                .append(
                    $('<div>').append(
                        $('<h1>').text("Hello then, ....")
                    )
                )
        )
        ;
}

function loadContractRow(e) {
    return $('<tr>')
        .attr('data-toggle', 'collapse')
        .attr('data-target', '#expanded-row-' + e.id)
        .append(
            $('<td>').text(e.orchardName),
            $('<td>').append(
                $('<progress>')
                    .attr('value', e.progress)
                    .attr('max', '100')
            ),
            $('<td>'),
            $('<td>')
        )
        ;
}

function loadContractDetails(data) {
    $('#id').html('Contract Id: ' + data.id);
    //$('#orchard').html('<b>' + data.orchard.yardName + '</b>');
    $('#amount').html('Amount: ' + data.amount);
    $('#in').html('In Date: ' + data.moveInDate);
    $('#out').html('Out Date: ' + data.moveOutDate);
    $('#broker').html('Broker: ' + data.broker.name);
    $('#number').html('Phone: ' + data.broker.contactInfo.phone);
    $('#edit').html('<a href="#"><i class="material-icons md-24 bee-board-icon">send</i></a>');
    $('#delete').html('<a href="#"><i class="material-icons md-24 bee-board-icon">delete</i></a>');
    $('#contacts').html('<a><i class="material-icons md-24 bee-board-icon" data-toggle="modal" data-target="#table-modal" onclick="getContacts(0)">person_outline</i></a>');
    $('#shipments').html('<a href="#"><i class="material-icons md-24 bee-board-icon">create</i></a>');
    $('#progress').html('% Fulfilled:<br/><progress style="width: 100%;" value="' + (data.amount - 10 * data.id) + '" max="100"></progress><hr/>');
}

function progressFormatter(value, row, index) {
    return [
        '<progress value="' + value + '" max="100" />'
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
    $('[data-toggle="tooltip"]').tooltip();
});

function updateTable(data) {
    $.ajax({
        dataType: "json",
        url: contractDtoList,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'GET',
        success: function (response) {
            $('#table-body').empty();
            $.each(response, function (i, e) {
                // put table html here.
            });
        }
    });
}