$(function () {
    $('#keyword').keypress(function (e) {
        var key = e.which; //e.which是按键的值
        if (key == 13) {
            var q = $(this).val();
            if (q && q != '') {
                window.location.href = '/search?keyword=' + q;
            }
        }
    });
});

function search() {
    var keyword = $('#keyword').val();
    if (q && q != '') {
        window.location.href = '/search?keyword=' + keyword;
    }
}
