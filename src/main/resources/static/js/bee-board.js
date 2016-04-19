/**
 * Created by Robert
 * on 11/3/2015.
 */
var url = "/beeboard/";
var addYard = url + "addYard";
var editYard = url + "yardedit/";
var deleteYard = url + "yarddelete/";
var inspections = url + "inspections/";

function assignHrefs(id) {
    var link = document.getElementById("editYard");
    link.href = "/yard/update/" + id;

    link = document.getElementById("deleteYard");
    link.href = "/yard/delete/" + id;

    link = document.getElementById("yardInspections");
    link.href = "/inspection/list/" + id;
}

function assignOwnerHrefs(ownerId) {
    var link = document.getElementById("yardOwner");
    link.href ="/owner/read/" + ownerId;
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

function loadCreateYardModal() {
    $.getJSON("/beeboard/createYard", function (data) {
        createYardForm(data);
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
            default:
                json[this.name] = this.value || '';
        }
    });
    return json;
}

function postYard() {
    var json = getYardJson($('#form').serializeArray());
    post(addYard, json, function () {
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
    getLocation();  //sets location of map
    //resizes and recenters maps whenever modal is opened
    $('#tabContent').on('shown.bs.tab', function (e){
        var curCenter=mapModal.getCenter();
        google.maps.event.trigger(mapModal, 'resize');
        mapModal.setCenter(curCenter);
    });
}

function createYardForm(data) {
    $('#form-modal-title').text("Create Yard");
    getYardForm(data, '/beeboard/addYard');
    getFormBody().append(getSubmitButton('Create'));
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
            return false;
        }
    });
}
//FORMATTER
function editFormatter(value, row, index) {
    var modelId = row["id"];
    return [
        '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
        '<i class="material-icons bee-board-icon">create</i>',
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
        tableArray = jsonArray[0]['yards'];
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

$(function () {
    initialize(); //initialize map
    recreateMap();
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
        assignHrefs(row["id"]);
        var owner = row["owner"];

        $("#name").html('<b>Yard Name: </b>' + row["yardName"]);
        $("#status").html('<b>Status: </b>' + row["status"]);
        $("#combo").html('<b>Combo: </b>' + row["combo"]);
        $("#owner").html('<b>Owner: </b>' + '<a id="yardOwner" href="#">' + owner["person"]["name"] + '</a>');
        var address = row["address"];
        $("#street").html('<b>Address: </b>' + address["street"]);
        $("#city").text(address["city"]);
        $("#state").text(address["state"]);
        $("#zip").text(address["zip"]);
        $("#singles").html('<b>Singles: </b>' + row["singles"]);
        $("#doubles").html('<b>Doubles: </b>' + row["doubles"]);
        $("#supers").html('<b>Supers: </b>' + row["supers"]);
        $("#duds").html('<b>Duds: </b>' + row["duds"]);
        assignOwnerHrefs(owner["id"]);

        if (row["lastVisit"] != null)
            $("#lastVisit").html('<b>Last Visit: </b>' + row["lastVisit"]);
        else
            $("#lastVisit").html('<b>No Inspections Recorded </b>');
        if (row["lastFedDate"] != null)
            $("#lastFedDate").html('<b>Last Fed: </b>' + row["lastFedDate"]);
        else
            $("#lastFedDate").html('<b>No Feed Date Recorded </b>');
    });


});

$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
    $('#regionDropdown').change(function(){
        regionName = $('#regionDropdown :selected').text();
        clearMarkers();
        recreateMap();
    });
});

//MODAL MAP
var x = document.getElementById("error");
var mapModal;
var markerModal;
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
        size: new google.maps.Size(45,45),
        origin: new google.maps.Point(0,0),
        anchor: new google.maps.Point(23,45)
    };
}
<!-- Sets Latitude and Longitude values -->
function setVals(position) {
    lat = position.coords.latitude;
    lng = position.coords.longitude;
    document.getElementById("latitudeModal").value = lat;
    document.getElementById("longitudeModal").value = lng;
    coords = new google.maps.LatLng(lat, lng);
    var mapOptions = {
        zoom: 15,
        center: coords,
        mapTypeControl: true,
        mapTypeId: google.maps.MapTypeId.HYBRID
    };
    mapModal = new google.maps.Map(
        document.getElementById("map-div-modal"), mapOptions
    );
    google.maps.event.addListener(mapModal, 'click', function (event) {
        placeMarker(event.latLng);
    });

    markerModal = new google.maps.Marker({
        position: coords,
        map: mapModal,
        icon: markerImage,
        title: "Current Location"
    });
}
function placeMarker(location) {
    lat = location.lat();
    lng = location.lng();
    setMarkerPosition(markerModal, lat, lng);
    document.getElementById("latitudeModal").value = location.lat();
    document.getElementById("longitudeModal").value = location.lng();
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

