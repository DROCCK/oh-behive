/**
 * @author Robert Wilk
 * Created on 2/9/2016.
 */
var url = "/pollination/";
var contractDtoList = url + "contracts";
var contract = url + "contract/";
var contacts = url + "contacts/";

function getEmptyTableHead() {
    var tableHead = $('#t-head');
    tableHead.empty();
    return tableHead;
}

function getEmptyTableBody() {
    var tableBody = $('#t-body');
    tableBody.empty();
    return tableBody;
}

function getContract(id) {
    var contractUrl = contract + id;
    alert(contractUrl);
    $.getJSON(contractUrl, function (data) {
        loadTableData(data);
    });
}

function getContacts(id) {
    var contactsUrl = contacts + id;
    $('#table-modal-title').text("Orchard's Contacts");
    $('#t-body').text("Loading...");
    $.getJSON(contactsUrl, function(data) {
        loadContactListModal(data);
    });
}

function loadListModal(data, headFunc, rowFunc) {
    getEmptyTableHead().append(headFunc());
    var tableBody = getEmptyTableBody();
    $.each(data, function(i, e) {
        tableBody.append(rowFunc(e));
    });
}

function loadContactListModal(data) {
    loadListModal(data, getContactHead, getContactRow);
}

function getContactRow(e) {
    return $('<tr>').append(
        $('<td>').text(e.name),
        $('<td>').text(e.contactInfo.email),
        $('<td>').text(e.contactInfo.phone)
    );
}

function getContactHead() {
    return $('<tr>').append(
        $('<td>').text('Name'),
        $('<td>').text('Email'),
        $('<td>').text('Phone')
    );
}

function loadShipmentListModal(data) {
    loadListModal(data, getShipmentHead, getShipmentRow);
}

function getShipmentHead() {
    // TODO add html for shipment head.
}

function getShipmentRow(e) {
    // TODO add html for shipment row.
}

function loadTableData(data) {
    alert(data);
    // Load full contract into table row here.
}

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

$(function () {
    $('#contract-table').bootstrapTable({}).on('click-row.bs.table', function (e, row, $element) {
        (function () {
            getContract()
        })();
    });
});