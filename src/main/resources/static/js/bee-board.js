/**
 * Created by Robert
 * on 11/3/2015.
 */
function assignHrefs(id) {
    var link = document.getElementById("editYard");
    link.href = "/yard/update/" + id;

    link = document.getElementById("deleteYard");
    link.href = "/yard/delete/" + id;

    link = document.getElementById("yardDrops");
    link.href = "/dropsite/list/" + id;

    link = document.getElementById("yardShipments");
    link.href = "/shipment/list/" + id;
}

function assignOwnerHrefs(ownerId) {
    var link = document.getElementById("yardOwner");
    link.href ="/owner/read/" + ownerId;
}