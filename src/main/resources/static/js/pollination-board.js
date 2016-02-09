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
    alert(contactsUrl);
    $.getJSON(contactsUrl, function(data) {
        loadContactListModal(data);
    });
}

function loadContactListModal(data) {
    alert(data);
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