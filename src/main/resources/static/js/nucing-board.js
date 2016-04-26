/**
 * Created by Connor
 * 3/12/2016.
 */

var url = "/nucing/";
var nucYardModal = "createNucYard";
var updateNucYard = "update/nucYard/";
var updateNucReportUrl = "update/nucReport/";
var nucReportUrl = 'nucReport/';
var editNucYardModal = 'editNucYard/';
var dto = "reports";


function init() {

    //$('#createButton').click(function () {
    //
    //    var event = {
    //        count: $('#count').val(),
    //        start: $('#date').val()
    //    };
    //
    //    $.ajax({
    //        url: "/nucing/test",
    //        type: "POST",
    //        dataType: 'json',
    //        data: event,
    //        beforeSend: function () {
    //            var token = $("meta[name='_csrf']").attr("content");
    //            var header = $("meta[name='_csrf_header']").attr("content");
    //            $(document).ajaxSend(function (e, xhr, options) {
    //                xhr.setRequestHeader(header, token);
    //            });
    //        },
    //        complete: function () {
    //            alert(eventJSON);
    //        },
    //        error: function (xhr, desc, err) {
    //            alert("error " + err + " " + desc + " " + xhr.responseText);
    //        }
    //    });
    //    $('#calendar').fullCalendar('refetchEvents');
    //    $('#count').val("");
    //});
    //
    //$('#date').val(getTodaysDate());

    $('#calendar').fullCalendar({
        events: '/nucing/events',
        color: 'yellow',
        droppable: true
    });

    addCSRFToken();
    updateTotalValues();

    $.ajaxSetup({
        type: 'POST',
        headers: {"cache-control": "no-cache"}
    });
}

function getEmptyFormBody() {
    var formBody = $('#form-body');
    formBody.empty();
    return formBody;
}

// Model part building functions

function getStatusSelector(data) {
    var select = $('<select>')
        .attr('id', 'status')
        .attr('class', 'form-control');
    $.each(data, function (i, e) {
        select.append(
            $('<option>')
                .attr('value', e)
                .text(e)
        )
    });
    return select;
}

function getYardSelector(data) {
    var select = $('<select>')
        .attr('id', 'yardName')
        .attr('class', 'form-control');
    $.each(data, function (i, e) {
        select.append(
            $('<option>')
                .attr('value', e.id)
                .text(e.yardName)
        )
    });
    return select;
}

function getPersonSelector(data, id) {
    var select = $('<select>')
        .attr('id', id)
        .attr('class', 'form-control');
    $.each(data, function (i, e) {
        select.append(
            $('<option>')
                .attr('value', e.name)
                .text(e.name)
        )
    });
    return select;
}

function getRegionSelector(data) {
    var select = $('<select>')
        .attr('class', 'form-control')
        .attr('id', 'region');
    $.each(data, function (i, e) {
        select.append(
            $('<option>')
                .attr("value", e)
                .text(e)
        )
    });
    return select;
}

function getFormGroup(for_id, label, type) {
    return getGroupDiv()
        .append(
        getFormGroupLabel(for_id, label),
        getFormGroupInput(for_id, type)
    );
}

function getGroupDiv() {
    return $('<div>')
        .attr('class', 'form-group');
}

function getFormGroupLabel(for_id, label) {
    return $('<label>')
        .attr('class', 'col-sm-4 control-label')
        .attr('for', for_id)
        .text(label);
}

function getFormGroupInput(for_id, type) {
    return $('<div>')
        .attr('class', 'col-sm-8')
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
        .attr('class', 'col-sm-8')
        .append(data);
}

function getFormGroupWithSelector(for_id, label, selector) {
    return getGroupDiv()
        .append(
        getFormGroupLabel(for_id, label),
        getFormGroupSelector(selector)
    );
}

// Nuc Report Modal

function loadNucReportModal(id) {
    var loadUrl = url + nucReportUrl + id;
    $.getJSON(loadUrl, function (data) {
        createNucReportForm(data, id);
    });
}

function createNucReportForm(data, id) {
    $('#form-modal-title').text("Nucing Report");
    var body = getEmptyFormBody();
    body.append(
        $('<ul>')
            .attr('class', 'nav nav-tabs')
            .attr('id', 'tabContent')
            .append(
            $('<li>')
                .attr('class', 'active')
                .append(
                $('<a>')
                    .attr('href', '#laidout')
                    .attr('data-toggle', 'tab')
                    .text('Lay Out')
            ),
            $('<li>')
                .append(
                $('<a>')
                    .attr('href', '#placed')
                    .attr('data-toggle', 'tab')
                    .text('Move In')
            ),
            $('<li>')
                .append(
                $('<a>')
                    .attr('href', '#supered')
                    .attr('data-toggle', 'tab')
                    .text('Super')
            ),
            $('<li>')
                .append(
                $('<a>')
                    .attr('href', '#split')
                    .attr('data-toggle', 'tab')
                    .text('Split')
            ),
            $('<li>')
                .append(
                $('<a>')
                    .attr('href', '#queensplaced')
                    .attr('data-toggle', 'tab')
                    .text('Place Queens')
            ),
            $('<li>')
                .append(
                $('<a>')
                    .attr('href', '#queenschecked')
                    .attr('data-toggle', 'tab')
                    .text('Check Queens')
            )
        ),
        $('<div>')
            .attr('class', 'tab-content')
            .append(
            $('<div>')
                .attr('class', 'pane tab-pane active')
                .attr('id', 'hidden')
                .append(
                getFormGroup('yardId', '', 'hidden')
            ),
            $('<div>')
                .attr('class', 'pane tab-pane active')
                .attr('id', 'laidout')
                .append(
                getFormGroup('dateLaidOut', 'Date Laid Out', 'date'),
                getFormGroup('notes', 'Notes', 'text')
            ),
            $('<div>')
                .attr('class', 'pane tab-pane')
                .attr('id', 'placed')
                .append(
                getFormGroup('dateBeesPlaced', "Date Bees Placed", 'date'),
                getFormGroup('initalCount', 'Hives Moved In', 'number')
            ),
            $('<div>')
                .attr('class', 'pane tab-pane')
                .attr('id', 'supered')
                .append(
                getFormGroup('dateBeesSupered', 'Date Bees Supered', 'date'),
                getFormGroup('countDuringSupering', '# of Hives after Supering', 'number'),
                getFormGroup('superCount', '# of Hives Supered', 'number')
            ),
            $('<div>')
                .attr('class', 'pane tab-pane')
                .attr('id', 'split')
                .append(
                getFormGroup('dateBeesSplit', 'Date Bees Split', 'date'),
                getFormGroup('nucCount', 'Number of Nucs', 'number'),
                getFormGroup('oldQueensCount', 'Number of Old Queens', 'number')
            ),
            $('<div>')
                .attr('class', 'pane tab-pane')
                .attr('id', 'queensplaced')
                .append(
                getFormGroup('queensPlaced', 'Number of Queens Placed', 'number')
            ),
            $('<div>')
                .attr('class', 'pane tab-pane')
                .attr('id', 'queenschecked')
                .append(
                getFormGroup('finalCount', "Total After Queen Check", 'number')
            )
        ),
        $('<div>')
            .attr('class', 'form-group')
            .append(
            $('<div>')
                .attr('class', 'col-sm-4 control-label')
                .append(
                $('<button>')
                    .attr('id', 'updateNucReport')
                    .attr('class', 'btn btn-primary')
                    //.attr('type', 'submit')
                    .text('Update')
            )
        )
    );
    // set data

    $('#yardId').val(id);
    $('#notes').val(data['notes']);
    $('#dateLaidOut').val(data['dateLaidOut']);
    $('#dateBeesPlaced').val(data['dateBeesPlaced']);
    $('#initalCount').val(data['initialCount']);
    $('#dateBeesSupered').val(data['dateBeesSupered']);
    $('#countDuringSupering').val(data['countDuringSupering']);
    $('#superCount').val(data['superCount']);
    $('#dateBeesSplit').val(data['dateBeesSplit']);
    $('#oldQueensCount').val(data['oldQueensCount']);
    $('#nucCount').val(data['nucCount']);
    $('#queensPlaced').val(data['queensPlaced']);
    $('#finalCount').val(data['finalCount']);

    $('#updateNucReport').click(function () {
        updateNucReport();
    })
}

function updateNucReport() {
    var nucReport = {
        yardId: $('#yardId').val(),
        dateLaidOut: $('#dateLaidOut').val(),
        notes: $('#notes').val(),
        dateBeesPlaced: $('#dateBeesPlaced').val(),
        initalCount: $('#initalCount').val(),
        dateBeesSupered: $('#dateBeesSupered').val(),
        countDuringSupering: $('#countDuringSupering').val(),
        superCount: $('#superCount').val(),
        dateBeesSplit: $('#dateBeesSplit').val(),
        nucCount: $('#nucCount').val(),
        oldQueensCount: $('#oldQueensCount').val(),
        queensPlaced: $('#queensPlaced').val(),
        finalCount: $('#finalCount').val()
    };

    $.ajax({
        url: url + updateNucReportUrl + getTimeStamp(),
        type: "POST",
        dataType: 'json',
        data: nucReport,
        beforeSend: addCSRFToken(),
        error: function (xhr, desc, err) {
            //alert("error " + err + " " + desc + " " + xhr.responseText);
            alert("Update nuc report");
        }
    })
}
// Nuc Yard Modal Functions

function loadCreateNucYardModal() {
    var createNucYardUrl = url + nucYardModal;
    $.getJSON(createNucYardUrl, function (data) {
        createNucYardForm(data);
    });
}

function createNucYardForm(data) {
    $('#form-modal-title').text("Create Yard");
    var body = getEmptyFormBody();
    var stati = getStatusSelector(data.stati);
    var owners = getPersonSelector(data.people, 'owner');
    var rentees = getPersonSelector(data.people, 'rentReceiver');
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
                getFormGroup('yardName', 'Name', 'text'),
                getFormGroup('maxHives', 'Max Hives', 'number'),
                getFormGroupWithSelector('status', 'Status', stati),
                getFormGroupWithSelector('owner', 'Owner', owners),
                getFormGroupWithSelector('rentReceiver', 'Rent Receiver', rentees)),
            $('<div>')
                .attr('class', 'pane tab-pane')
                .attr('id', 'location')
                .append(
                getFormGroup('street', 'Street', 'text'),
                getFormGroup('apt', 'Suite', 'text'),
                getFormGroup('city', 'City', 'text'),
                getFormGroup('state', 'State', 'text'),
                getFormGroup('zip', 'Zip', 'text'),
                getFormGroupWithSelector('region', 'Region', regions),
                getFormGroup('longitudeModal', 'Longitude', 'text'),
                getFormGroup('latitudeModal', 'Latitude', 'text'),
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
                getFormGroup('combo', 'Combination or Key'),
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
                    .attr('id', 'createNucYard')
                    .attr('class', 'btn btn-primary')
                    //.attr('type', 'submit')
                    .text('Create')
            )
        )
    )
    ;

    getLocation();
    $('#tabContent').on('shown.bs.tab', function (e) {
        if (e.target.id == "locationTab") {
            google.maps.event.trigger(map, 'resize');
        }
    });

    $('#createNucYard').click(function () {
            createNucYard();
        }
    );
}


function loadEditNucYardModal() {
    var editNucYardUrl = url + editNucYardModal;
    $.getJSON(editNucYardUrl, function (data) {
        editNucYardForm(data);
    });
}

function editNucYardForm(data) {
    $('#form-modal-title').text("Edit Yard");
    var body = getEmptyFormBody();
    var nucYards = getYardSelector(data.yards);
    var stati = getStatusSelector(data.stati);
    var owners = getPersonSelector(data.people, 'owner');
    var rentees = getPersonSelector(data.people, 'rentReceiver');
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
                getFormGroupWithSelector('yardName', 'Name', nucYards),
                getFormGroup('maxHives', 'Max Hives', 'number'),
                getFormGroupWithSelector('status', 'Status', stati),
                getFormGroupWithSelector('owner', 'Owner', owners),
                getFormGroupWithSelector('rentReceiver', 'Rent Receiver', rentees)),
            $('<div>')
                .attr('class', 'pane tab-pane')
                .attr('id', 'location')
                .append(
                getFormGroup('street', 'Street', 'text'),
                getFormGroup('apt', 'Suite', 'text'),
                getFormGroup('city', 'City', 'text'),
                getFormGroup('state', 'State', 'text'),
                getFormGroup('zip', 'Zip', 'text'),
                getFormGroupWithSelector('region', 'Region', regions),
                getFormGroup('longitudeModal', 'Longitude', 'number'),
                getFormGroup('latitudeModal', 'Latitude', 'number'),
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
                getFormGroup('combo', 'Combination or Key'),
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
                    .attr('id', 'createNucYard')
                    .attr('class', 'btn btn-primary')
                    //.attr('type', 'submit')
                    .text('Create')
            )
        )
    )
    ;

    getLocation();
    $('#tabContent').on('shown.bs.tab', function (e) {
        if (e.target.id == "locationTab") {
            google.maps.event.trigger(map, 'resize');
        }
    });

    $('#createNucYard').click(function () {
            createNucYard();
        }
    );

    $("#yardName").on("change", function() {
        updateEditValues($('#yardName').find("option:selected").val());
    })
}


function updateEditValues(id) {
    $.getJSON("/nucing/getYard/" + id, function(data) {
        $("#maxHives").val(data.maxHives);
        $("#rentReceiver").val(data.rentReceiver.name);
        $("#owner").val(data.owner.person.name);
        $("#street").val(data.address.street);
        $("#apt").val(data.address.apt);
        $("#city").val(data.address.city);
        $("#state").val(data.address.state);
        $("#zip").val(data.address.zip);
        //TODO: REGION
        $("#longitudeModal").val(data.longitude);
        $("#latitudeModal").val(data.latitude);
        $('#combo').val(data.combo);
        $('#accessNotes').val(data.accessNotes);

        setMarkerPosition(marker, $("#latitudeModal").val(), $("#longitudeModal").val());
    })
}


function createNucYard() {
    var NucYard = {
        yardName: $('#yardName').val(),
        maxHives: $('#maxHives').val(),
        status: $('#status').find('option:selected').val(),
        owner: $('#owner').find('option:selected').val(),
        rentReceiver: $('#rentReceiver').find('option:selected').val(),
        region: $('#region').find('option:selected').val(),
        longitude: $('#longitudeModal').val(),
        latitude: $('#latitudeModal').val(),
        combo: $('#combo').val(),
        accessNotes: $('accessNotes').val(),
        street: $('#street').val(),
        apt: $('#apt').val(),
        city: $('#city').val(),
        state: $('#state').val(),
        zip: $('#zip').val()
    };

    $.ajax({
        url: url + updateNucYard + getTimeStamp(),
        type: "POST",
        dataType: 'json',
        data: NucYard,
        beforeSend: addCSRFToken(),
        complete: function () {
            //alert(NucYard);
        },
        error: function (xhr, desc, err) {
            //alert("error " + err + " " + desc + " " + xhr.responseText);
            alert("Created new nuc yard");
        }
    });
}

// Map Functions

function getLocation() {

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(setVals, showError);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
    markerImage = {
        url: 'http://i.imgur.com/ALU8OuA.png',
        size: new google.maps.Size(45, 45),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(23, 45)
    };
}

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

function placeMarker(location) {
    lat = location.lat();
    lng = location.lng();
    setMarkerPosition(marker, lat, lng);
    document.getElementById("latitudeModal").value = location.lat();
    document.getElementById("longitudeModal").value = location.lng();
}

function setMarkerPosition(marker, lat, lng) {
    marker.setPosition(
        new google.maps.LatLng(
            lat,
            lng)
    );
}

// Helper functions

function getTodaysDate() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth();
    var yyyy = today.getFullYear();
    var format = yyyy + '-' + addZero(mm + 1) + '-' + addZero(dd);

    return format;
}

function getTimeStamp() {
    var today = new Date();
    var ms = today.getMilliseconds();
    var s = today.getSeconds();
    var m = today.getMinutes();
    var h = today.getHours();
    var dd = today.getDate();
    var mm = today.getMonth();
    var yyyy = today.getFullYear();
    var format = yyyy + "" + mm + "" + dd + "" + h + "" + m + '' + s + "" + ms;

    return format;
}

function addZero(s) {
    s = s + '';
    if (s.length === 1) s = '0' + s;
    return s;
}

function getCSRFTokenValue() {
    return $('#csrf-token').val();
}

function addCSRFToken() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}
// Table functions

window.operateEvents = {
    'click .edit': function (e, value, row, index) {
        //$.ajax(url + nucReportUrl + row["id"], function (data) {
        //    alert("Got It");
        //});
    }
};

function editFormatter(value, row, index) {
    var modelId = row["id"];
    return [
        '<a class="edit ml10" data-toggle="modal" data-target="#form-modal" onclick="loadNucReportModal(' + row["yardId"] + ')" href="javascript:void(0)" title="Edit">',
        'Edit Report',
        '<i class="material-icons bee-board-icon">create</i>',
        '</a>'
    ].join('');
}

function updateTotalValues() {
    $.getJSON("/nucing/reportSum", function (result) {
            $('#sumInitialCount').text(result['initialCount']);
            $("#sumSuperCount").text(result["countDuringSupering"]);
            $("#sumSupers").text(result["superCount"]);
            $("#sumOldQueens").text(result["oldQueensCount"]);
            $("#sumNucCount").text(result["nucCount"]);
            $("#sumQueensPlaced").text(result["queensPlaced"]);
            $("#sumFinalCount").text(result["finalCount"]);
        }
    );
}