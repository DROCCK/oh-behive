/**
 * Created by Rob on 2/19/2016.
 */
function editFormatter(value, row, index) {
    var modelId = row["id"];
    return [
        '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
        '<i class="material-icons bee-board-icon">create</i>',
        '</a>'
    ].join('');
}

function editOrchardFormatter(value, row, index){
    var modelId = row["id"];
    return [
        '<a class="edit ml10" href="#" title="Edit" data-toggle="modal" data-target="#form-modal"' +
        'onclick="loadEditContractOrchardModal(' + modelId + ')">',
        '<i class="material-icons bee-board-icon">create</i>',
        '</a>'
    ].join('');
}

function checkFormatter(value, row, index) {
    return [
        '<a class="remove ml10 bee-board-icon" href="#" title="Complete"' +
        'onclick="fillOrchard(' + row["id"] + ')">',
        ' <i class="material-icons bee-board-icon">check</i>',
        '</a>'
    ].join('');
}

function recycleFormatter(value, row, index) {
    return [
        '<a class="remove ml10 bee-board-icon" href="#" title="Renew"' +
        'onclick="emptyOrchard(' + row["id"] + ')">',
        ' <i class="material-icons bee-board-icon">autorenew</i>',
        '</a>'
    ].join('');
}

function deleteFormatter(value, row, index) {
    return [
        '<a class="remove ml10 bee-board-icon" href="javascript:void(0)" title="Remove">',
        ' <i class="material-icons bee-board-icon">delete</i>',
        '</a>'
    ].join('');
}

$(function () {
    $('#contract-table').bootstrapTable({}).on('click-row.bs.table', function (e, row, $element) {
        tableRowClick(e, row, $element);
    });
});
