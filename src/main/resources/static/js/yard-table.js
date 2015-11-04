/**
    * @author Robert Wilk
    * Created on 10/29/2015.
 */
$('.table > tr').click(function () {
    alert(this.value);
});

$(function () {
    $(".clickableRow").on("click", function () {
        alert("abc " + this.value);
    });
});

function operateFormatter(value, row, index) {
    return [
        '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
        '<i class="material-icons bee-board-icon">create</i>',
        '</a>',
        '<a class="remove ml10" href="javascript:void(0)" title="Remove">',
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

function hide() {
    document.getElementById('yard-prompt').style.display = "none";
    document.getElementById('map-div').style.display = "block";
}



$(function () {
    $('#yard-table').bootstrapTable({}).on('click-row.bs.table', function (e, row, $element) {
        $("#name").text(row["yardName"]);
        $("#status").text(row["status"]);
        $("#combo").text(row["combo"]);
        var address = row["address"];
        $("#street").text(address["street"]);
        $("#city").text(address["city"]);
        $("#state").text(address["state"]);
        $("zip").text(address["zip"]);
    });
});
