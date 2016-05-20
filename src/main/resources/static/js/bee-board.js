/**
 * Created by Robert
 * on 11/3/2015.
 */
var url = "/beeboard/";
var addYard = url + "addYard";
var deleteYard = url + "yarddelete/";
var inspections = url + "inspections/";
var addInspection = url + "addInspection";

function assignHrefs(id) {
    /*
    var link = document.getElementById("editYard");
    link.href = "/yard/update/" + id;

    var link = document.getElementById("deleteYard");
    link.href = "/yard/delete/" + id;
    */
    link = document.getElementById("yardInspections");
    link.href = "/inspection/list/" + id;
}

function assignOwnerHrefs(ownerId) {
    //console.log(document.getElementById("yardOwner"));
    var link = document.getElementById("yardOwner");
    link.href ="/owner/read/" + ownerId;
}

function putInputValue(name, value) {
    $('#' + name).val(value == null ? '' : value);
}

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

function getFormBody() {
    return $('#form-body');
}

function getEmptyFormBody() {
    var formBody = getFormBody();
    formBody.empty();
    return formBody;
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
function getFormGroupWithTextArea(for_id, label, val){
    return getGroupDiv()
        .append(
            getFormGroupLabel(for_id,label),
            getFormGroupTextArea(for_id, val)
        )
}
function getMapFormGroup(for_id){
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

function getFormGroupTextArea(for_id, val){
    return $('<div>')
        .attr('class', 'col-sm-10')
        .append(
            $('<textarea>')
                .attr('class', 'form-control')
                .attr('id', for_id)
                .attr('name', for_id)
                .attr('rows', 4)
                .attr('cols', 20)
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

function getHiddenInput(name, i){
    return getFormGroupInput(name, "hidden", i);
}
function selectOption(id) {
    $('#' + id).prop('selected', true);
}

function loadCreateYardModal() {
    $.getJSON("/beeboard/createYard", function (data) {
        createYardForm(data);
    });
}

function loadEditYardModal(id) {
    $.getJSON("/beeboard/editYard/" + id, function (data) {
        editYardForm(data);
    });
}

function loadCreateInspectionModal(id) {
    $.getJSON("/beeboard/createInspection/"+id, function (data) {
        createInspectionForm(data);
    });
}

function loadChangeToInactiveModal(id){
    $.getJSON("/beeboard/changeInactive/"+id, function(data){
       createChangeToInactiveForm(data);
    });
}

function getYardJson(form) {
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
            case 'longitudeModal':
                json['longitude'] = this.value;
                break;
            case 'latitudeModal':
                json['latitude'] = this.value;
                break;
            default:
                json[this.name] = this.value || '';
        }
    });
    return json;
}

function getInspectionJson(form, yardId){
    var json = {};
    $.each(form, function(){
        switch (this.name){
            default:
                json[this.name] = this.value || '';
        }
    });
    json["yard"]=yardId;
    console.log(json);
    return json;
}

function postYard() {
    var json = getYardJson($('#form').serializeArray());
    post(addYard, json, function () {
        $('#id').remove();
    });
}

function postInspection(yardId){
    var json = getInspectionJson($('#form').serializeArray(), yardId);
    post(addInspection, json, function () {
        $('#id').remove();
    });
}

function getYardForm(data, action) {
    var form = $('#form');
    form.attr('action', action);
    form.submit(function (event) {
        event.preventDefault();
        postYard();
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
                            getFormGroup('longitudeModal', 'Longitude', 'text'),
                            getFormGroup('latitudeModal', 'Latitude', 'text'),
                            getMapFormGroup('map-div-modal')
                        ),
                    getTabPane('access', false)
                        .append(
                            getFormGroup('combo', 'Combination or Key', 'text'),
                            getFormGroup('accessNotes', 'Access Notes', 'text')
                        )
                )
        );
}

function getInspectionForm(data, action){
    var form = $('#form');
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth()+1;
    var year = date.getFullYear();
    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;
    //console.log(year+"-"+month+"-"+day);
    form.attr('action', action);
    form.submit(function (event) {
        event.preventDefault();
        postInspection(data.yard.id);
        return false;
    });
    getEmptyFormBody()
        .append(
            getFormGroup('date', 'Date', 'date', year+"-"+month+"-"+day),
            getFormGroup('singles', 'Singles', 'text'),
            getFormGroup('doubles', 'Doubles', 'text'),
            getFormGroup('supers', 'Supers', 'text'),
            getFormGroup('duds', 'Duds', 'text'),
            getFormGroup('medication', 'Medication Used', 'text'),
            getFormGroup('isFed', 'Where the bees fed?', 'checkbox', 'fed'),
            getFormGroupWithTextArea('notes', 'Notes')
        );
}

function getEmptyYardForm(action){
    var form = $('#form');
    form.attr('action', action);
    form.submit(function (event) {
        event.preventDefault();
        postYard();
        return false;
    });
    getEmptyFormBody();
}

function resizeAndCenterMap(){
    //resizes and recenters maps whenever modal is opened
    $('#tabContent').on('shown.bs.tab', function (e){
        var curCenter=mapModal.getCenter();
        google.maps.event.trigger(mapModal, 'resize');
        mapModal.setCenter(curCenter);
    });
}

function fillYardForm(data){
    $('#form').append(getHiddenIdInput(data.id));
    $('#form').append(getHiddenInput('singles', data.singles));
    $('#form').append(getHiddenInput('doubles', data.doubles));
    $('#form').append(getHiddenInput('supers', data.supers));
    $('#form').append(getHiddenInput('duds', data.duds));
    $('#form').append(getHiddenInput('lastVisit', data.lastVisit));
    $('#form').append(getHiddenInput('lastFedDate', data.lastFedDate));
    putInputValue('yardName', data.yardName);
    putInputValue('maxHives', data.maxHives);
    putInputValue('street', data.address.street);
    putInputValue('suite', data.address.apt);
    putInputValue('city', data.address.city);
    putInputValue('state', data.address.state);
    putInputValue('zip', data.address.zip);
    putInputValue('longitudeModal', data.longitude);
    putInputValue('latitudeModal', data.latitude);
    putInputValue('combo', data.combo);
    putInputValue('accessNotes', data.accessNotes);
    selectOption(data.status);
    selectOption(data.owner.name);
    selectOption(data.rentReceiver.name);
    selectOption(data.region.name);
}

function fillEmptyYardInactiveForm(data){
    $('#form').append(getHiddenIdInput(data.id));
    $('#form').append(getHiddenInput('singles', data.singles));
    $('#form').append(getHiddenInput('doubles', data.doubles));
    $('#form').append(getHiddenInput('supers', data.supers));
    $('#form').append(getHiddenInput('duds', data.duds));
    $('#form').append(getHiddenInput('lastVisit', data.lastVisit));
    $('#form').append(getHiddenInput('lastFedDate', data.lastFedDate));
    $('#form').append(getHiddenInput('yardName', data.yardName));
    $('#form').append(getHiddenInput('maxHives', data.maxHives));
    $('#form').append(getHiddenInput('street', data.address.street));
    $('#form').append(getHiddenInput('suite', data.address.apt));
    $('#form').append(getHiddenInput('city', data.address.city));
    $('#form').append(getHiddenInput('state', data.address.state));
    $('#form').append(getHiddenInput('zip', data.address.zip));
    $('#form').append(getHiddenInput('longitudeModal', data.longitude));
    $('#form').append(getHiddenInput('latitudeModal', data.latitude));
    $('#form').append(getHiddenInput('combo', data.combo));
    $('#form').append(getHiddenInput('accessNotes', data.accessNotes));
    $('#form').append(getHiddenInput('status', 'INACTIVE'));
    $('#form').append(getHiddenInput('owner', data.owner.id));
    $('#form').append(getHiddenInput('rentReceiver', data.rentReceiver.id));
    $('#form').append(getHiddenInput('region', data.region.name));
}

function fillInspectionForm(data){
    putInputValue('singles', data.yard.singles);
    putInputValue('doubles', data.yard.doubles);
    putInputValue('supers', data.yard.supers);
    putInputValue('duds', data.yard.duds);
}

function createYardForm(data) {
    $('#form-modal-title').text("Create Yard");
    getYardForm(data, '/beeboard/addYard');
    getFormBody().append(getSubmitButton('Create'));
    getLocation();  //sets location of map
    resizeAndCenterMap();
}

function editYardForm(data){
    $('#form-modal-title').text("Edit Yard");
    getYardForm(data.yardCreateDTO, '/beeboard/addYard');
    getFormBody().append(getSubmitButton('Save'));
    fillYardForm(data.yard);
    initializeEditYardMap(data.yard.latitude, data.yard.longitude);
    resizeAndCenterMap();
}

function createInspectionForm(data){
    $('#form-modal-title').text("Create Inspection for "+data.yard.yardName);
    getInspectionForm(data, '/beeboard/addInspection');
    getFormBody().append(getSubmitButton('Create'));
    fillInspectionForm(data);
}

function createChangeToInactiveForm(data){
    $('#form-modal-title').text("Are you sure you want to make "+data.yard.yardName+" inactive?");
    getEmptyYardForm(data, '/beeboard/addYard');
    fillEmptyYardInactiveForm(data.yard);
    getFormBody().append(getSubmitButton('Yes'));
}
//POST FUNCTION
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
            clearMarkers();
            recreateMap();
            recalculateTotals();
            return false;
        }
    });
}
//FORMATTER

function editFormatter(value, row, index) {
    var modelId = row["id"];
    return [
        '<a data-toggle="modal" data-target="#form-modal" onclick="loadEditYardModal('+modelId+')">' +
            '<i class="material-icons bee-board-icon">' +
            ' create ' +
            '</i></a>'
    ].join('');
}

function createInspectionFormatter(value, row, index){
    var modelId = row["id"];
    return [
        '<a data-toggle="modal" data-target="#form-modal" onclick="loadCreateInspectionModal('+row["id"]+')">' +
        '<i class="material-icons bee-board-icon" data-toggle="tooltip">' +
        'add_circle_outline' +
        '</i> </a>'
    ].join('');
}

function deleteFormatter(value, row, index) {
    var modelId=row["id"];
    return [
        '<a data-toggle="modal" data-target="#form-modal" onclick="loadChangeToInactiveModal('+modelId+')">' +
        '<i class="material-icons bee-board-icon">' +
        ' delete ' +
        '</i></a>'
    ].join('');
}

window.operateEvents = {
    'click .edit': function (e, value, row, index) {
        location.href = "/yard/update/" + row["id"];
    },
    'click .remove': function (e, value, row, index) {
        location.href = "/yard/delete/" + row["id"];
    }
};

//DESCRIPTION & Map creation/editing
var jsonArray;
var regionName = "All";
var tableArray;

function recreateMap(){
    var json = $.getJSON('/dashboard/beeboard/json', function (data) {
        //console.log(json);
        var labels;
        jsonArray = data; //moves data array to var
        tableArray = jsonArray[0]['yards']; //array that holds beeboard table data
        if(regionName=="All"){
            tableArray = [];
            for(var j = 0; jsonArray.length>j; j++){
                var tempArrLength = jsonArray[j]["yards"].length;
                for (var i = 0; tempArrLength > i; i++) {
                    labels = '<div id="mapNote">' +
                        '<h2 id="Yard Info">Yard Info</h2>' +
                        '<p>Singles: ' + jsonArray[j]["yards"][i]["singles"] + '<br/>' +
                        'Doubles: ' + jsonArray[j]["yards"][i]["doubles"] + '<br/>' +
                        'Supers: ' + jsonArray[j]["yards"][i]["supers"] + '<br/>' +
                        '<a href="/inspection/list/' + jsonArray[j]['yards'][i]['id'] + '">' +
                        'Check Inspections</a>' +
                        '</p>' +
                        '</div>';
                    createMarker(jsonArray[j]['yards'][i]['longitude'], jsonArray[j]['yards'][i]['latitude'], labels); //Cycles through drops and places markers
                    tableArray.push(jsonArray[j]['yards'][i]);
                }
            }
            $('#yard-table').bootstrapTable('load', tableArray);
            $('#yard-table').bootstrapTable('hideLoading');
        }
        else{
            for(var j = 0; jsonArray.length>j; j++){
                if(jsonArray[j]["name"]==regionName){
                    var index = j;
                }
            }
            var tempArrLength = jsonArray[index]["yards"].length;
            for (var i = 0; tempArrLength > i; i++) {
                labels = '<div id="mapNote">' +
                    '<h2 id="Yard Info">Yard Info</h2>' +
                    '<p>Singles: ' + jsonArray[index]["yards"][i]["singles"] + '<br/>' +
                    'Doubles: ' + jsonArray[index]["yards"][i]["doubles"] + '<br/>' +
                    'Supers: ' + jsonArray[index]["yards"][i]["supers"] + '<br/>' +
                    '<a href="/inspection/list/' + jsonArray[index]['yards'][i]['id'] + '">' +
                    'Check Inspections</a>' +
                    '</p>' +
                    '</div>';
                createMarker(jsonArray[index]['yards'][i]['longitude'], jsonArray[index]['yards'][i]['latitude'], labels); //Cycles through drops and places markers
            }
            tableArray = jsonArray[index]['yards'];
            $('#yard-table').bootstrapTable('load', tableArray);
        }
    });
}

function recalculateTotals(){
    $.getJSON("/beeboard/sums", function(data){
        $('#singlesTotal').text("Singles: "+data['singles']);
        $('#doublesTotal').text("Doubles: "+data['doubles']);
        $('#supersTotal').text("Supers: "+data['supers']);
        $('#dudsTotal').text("Duds: "+data['duds']);
        $('#total').text("Total: "+data['total']);

        $('#singlesPercent').text("Singles: %"+data['singlesPercent']);
        $('#doublesPercent').text("Doubles: %"+data['doublesPercent']);
        $('#supersPercent').text("Supers: %"+data['supersPercent']);
        $('#dudsPercent').text("Duds: %"+data['dudsPercent']);
    });
}

$(function () {
    initialize(); //initialize map
    recreateMap();
    recalculateTotals();
    $('#yard-table').bootstrapTable({}).on('click-row.bs.table', function (e, row, $element) {
        (function () {
            document.getElementById('yard-prompt').style.display = "none";
            document.getElementById('map-div').style.display = "block";
            document.getElementById('infoPanel').style.display = "block";
            var jsonLength = jsonArray.length;
            var arrLength;
            var yardIndex;
            var regionIndex;
            //Recenters map
            var yardId = row['id'];
            for(var j = 0; jsonLength > j; j++){
                arrLength = jsonArray[j]['yards'].length;
                for(var i = 0; arrLength > i; i++){
                    if(jsonArray[j]['yards'][i]['id']==yardId){
                        yardIndex = i;
                        regionIndex = j;
                    }
                }
            }
            var lat = jsonArray[regionIndex]['yards'][yardIndex]['latitude'];
            var long = jsonArray[regionIndex]['yards'][yardIndex]['longitude'];
            //console.log(lat +" "+long);
            recenter(lat, long);
        })();
        $("#name").html('<b>Yard Name: </b>' + row["yardName"]);
        $("#status").html('<b>Status: </b>' + row["status"]);
        $("#combo").html('<b>Combo: </b>' + row["combo"]);
        $("#owner").html('<b>Owner: </b>' + '<a id="yardOwner" href="#">' + row["owner"]["person"]["name"] + '</a>');
        var address = row["address"];
        $("#street").html('<b>Address: </b>' + address["street"]);
        $("#city").text(address["city"]);
        $("#state").text(address["state"]);
        $("#zip").text(address["zip"]);
        $("#singles").html('<b>Singles: </b>' + row["singles"]);
        $("#doubles").html('<b>Doubles: </b>' + row["doubles"]);
        $("#supers").html('<b>Supers: </b>' + row["supers"]);
        $("#duds").html('<b>Duds: </b>' + row["duds"]);
        assignOwnerHrefs(row["owner"]["id"]);
        //description buttons
        $("#editDescButton").html('<a data-toggle="modal" data-target="#form-modal" onclick="loadEditYardModal('+row["id"]+')">' +
            '<i class="material-icons bee-board-icon" data-toggle="tooltip">' +
            ' create ' +
            '</i></a>');
        $("#inspectionsDescButton").html('<a data-toggle="modal" data-target="#form-modal" onclick="loadCreateInspectionModal('+row["id"]+')">' +
            '<i class="material-icons bee-board-icon" data-toggle="tooltip">' +
            'add_circle_outline' +
            '</i> </a>');
        if (row["lastVisit"] != null)
            $("#lastVisit").html('<b>Last Visit: </b>' + row["lastVisit"]);
        else
            $("#lastVisit").html('<b>No Inspections Recorded </b>');
        if (row["lastFedDate"] != null)
            $("#lastFedDate").html('<b>Last Fed: </b>' + row["lastFedDate"]);
        else
            $("#lastFedDate").html('<b>No Feed Date Recorded </b>');
        //assignHrefs(row["id"]);
    });

    $('[data-toggle="tooltip"]').tooltip();
    //Region Dropdown
    $('#regionDropdown').change(function(){
        regionName = $('#regionDropdown :selected').text();
        clearMarkers();
        recreateMap();
    });
});