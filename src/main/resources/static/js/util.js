/**
 * Created by Robert on 3/12/2016.
 */
function clearModal(name) {
    $(name).empty();
}

function closeModal(name) {
    $(name).modal('toggle');
}

function clearAndCloseModal(name) {
    clearModal();
    closeModal(name);
}