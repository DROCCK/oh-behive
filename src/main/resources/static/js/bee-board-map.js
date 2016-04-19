/**
 * Created by Oscar on 4/16/2016.
 */
var x = document.getElementById("error");
var coords;
var markerImage;
var shape;
var markers = [];
var bounds;
var map;
var mapOptions;

//Creates and centers map
function initialize() { //Passed in longitude and latitude are just the position of the first drop of the yard.
    //generate the bee marker image
    markerImage = {
        url: 'http://i.imgur.com/ALU8OuA.png',
        size: new google.maps.Size(45, 45),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(23, 45)
    };
    shape = {
        coords: [1, 1, 1, 45, 45, 45, 45, 1],
        type: 'poly'
    };
    bounds = new google.maps.LatLngBounds();
    mapOptions = {
        mapTypeControl: true,
        mapTypeId: google.maps.MapTypeId.HYBRID
    };
    map = new google.maps.Map(
        document.getElementById("map-div"), mapOptions
    );
}

function recenter(latitude, longitude) { //used to recenter the map whenever a new row is clicked
    coords = new google.maps.LatLng(latitude, longitude);
    map.setZoom(15);
    map.panTo(coords);
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
//Creates Markers
function createMarker(long, lat, windowLabel) {
    //create info window
    var infoWindow = new google.maps.InfoWindow({
        content: windowLabel.toString(),
        maxWidth: 200
    });
    var latlng = new google.maps.LatLng(lat, long);
    var marker = new google.maps.Marker({
        position: latlng,
        icon: markerImage,
        shape: shape
    });
    marker.setMap(map); //must set its map to the pages' map
    //adds listener to each marker during creation
    marker.addListener('click', function () {
        infoWindow.open(map, marker);
    });
    markers.push(marker);
    bounds.extend(marker.getPosition());
    map.fitBounds(bounds);
}

function clearMarkers(){
    for (var i = 0; markers.length > i; i++) {
        markers[i].setMap(null);
    }
    markers = [];
    bounds = new google.maps.LatLngBounds();    //creates new bounds so that when new markers are made, new bounds are made.
}