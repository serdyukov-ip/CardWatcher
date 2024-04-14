window.onload = function() {
    var confirmUpdate = document.getElementById('confirmUpdate');
    var confirmDelete = document.getElementById('confirmDelete');

    if (confirmUpdate) {
        confirmUpdate.addEventListener('click', function () {
            document.getElementById('updateForm').submit();
        });
    }

    if (confirmDelete) {
        confirmDelete.addEventListener('click', function () {
            document.getElementById('deleteForm').submit();
        });
    }

    if ($('.datepicker').length > 0) {
        $('.datepicker').datepicker({
            weekStart: 1,
            color: 'red'
        });
    }
}

jQuery(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
});

$(document).ready(function() {
    $('#paymentStatusName').change(function() {
        $('#paymentStatusId').val($(this).val());
    });
});
