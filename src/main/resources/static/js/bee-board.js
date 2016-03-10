/**
 * Created by Robert
 * on 11/3/2015.
 */
var url = "/beeboard/";
var createYard = url + "yardcreate";
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

function getEmptyFormBody() {
    var formBody = $('#form-body');
    formBody.empty();
    return formBody;
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


function loadCreateYardModal() {
    $.getJSON("/beeboard/createyard", function (data) {
        createYardForm(data);
    });
}

function createYardForm(data) {
    $('#form-modal-title').text("Create Yard");
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
                    .attr('id', "locationTab")
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
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'name')
                        .text("Name"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'name')
                            .attr('type', 'text')
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'maxHives')
                        .text("Max Hives"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'maxHives')
                            .attr('type', 'text')
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'status')
                        .text("Status"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        stati
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'owner')
                        .text("Owner"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        owners
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'rentReceiver')
                        .text("Rent Receiver"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        rentees
                    )
                )
            ),
            $('<div>')
                .attr('class', 'pane tab-pane')
                .attr('id', 'location')
                .append(
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'street')
                        .text("Street"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'street')
                            .attr('type', 'text')
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'suite')
                        .text("Suite"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'suite')
                            .attr('type', 'text')
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'city')
                        .text("City"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'city')
                            .attr('type', 'text')
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'state')
                        .text("State"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'state')
                            .attr('type', 'text')
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'zip')
                        .text("Zip"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'zip')
                            .attr('type', 'text')
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'region')
                        .text("Region"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        regions
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'longitudeModal')
                        .text("Longitude"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'longitudeModal')
                            .attr('type', 'text')
                    )
                ),

                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'latitudeModal')
                        .text("Latitude"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'latitudeModal')
                            .attr('type', 'text')
                    )
                ),

                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<div>')
                        .attr('class', 'col-sm-1'),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .attr('id', 'map-div-modal')
                        .attr('style', 'height:300px'),
                    $('<div>')
                        .attr('class', 'col-sm-1')
                )
            ),
            $('<div>')
                .attr('class', 'pane tab-pane')
                .attr('id', 'access')
                .append(
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'combo')
                        .text("Combination or Key"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'combo')
                            .attr('type', 'text')
                    )
                ),
                $('<div>')
                    .attr('class', 'form-group')
                    .append(
                    $('<label>')
                        .attr('class', 'col-sm-2 control-label')
                        .attr('for', 'accessNotes')
                        .text("Access Notes"),
                    $('<div>')
                        .attr('class', 'col-sm-10')
                        .append(
                        $('<input>')
                            .attr('class', 'form-control')
                            .attr('id', 'accessNotes')
                            .attr('type', 'text')
                    )
                )
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
    getLocation();
    $('#tabContent').on('shown.bs.tab', function (e){
        if(e.target.id== "locationTab"){
            google.maps.event.trigger(map, 'resize');
        }
    });
}
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

