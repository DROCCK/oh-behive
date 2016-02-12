/**
 * @author Robert Wilk
 * Created on 2/9/2016.
 */
var url = "/pollination/";
var contractDtoList = url + "contracts";
var contract = url + "contract/";
var contacts = url + "contacts/";

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

function loadContactListModal(data) {
    var tableHead = $('#t-head');
    tableHead.empty();
    var head = $('<tr>').append(
        $('<td>').text('Name'),
        $('<td>').text('Email'),
        $('<td>').text('Phone')
    );
    tableHead.append(head);
    var tableBody = $('#t-body');
    tableBody.empty();
    $.each(data, function(i, e) {
        var row = $('<tr>').append(
            $('<td>').text("Person's name"),
            $('<td>').text(e.email),
            $('<td>').text(e.phone)
        );
        tableBody.append(row);
    });
    // Load modal window with list of contacts.
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