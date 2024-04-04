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
}

jQuery(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
});



