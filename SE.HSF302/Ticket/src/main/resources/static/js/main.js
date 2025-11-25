$(document).ready(function() {
    // Thiết lập timeout cho các thông báo
    setTimeout(function() {
        $('.alert').alert('close');
    }, 5000);

    // Validate form đặt vé
    $('#bookingForm').submit(function(e) {
        var selectedSeats = $('input[name="seatIds"]:checked').length;

        if (selectedSeats === 0) {
            e.preventDefault();
            alert('Vui lòng chọn ít nhất một ghế');
            return false;
        }

        // Cập nhật số lượng vé
        $('#totalTickets').val(selectedSeats);

        return true;
    });

    // Hiệu ứng highlight cho các ghế đã chọn
    $('input[name="seatIds"]').change(function() {
        if($(this).is(':checked')) {
            $(this).parent().addClass('bg-success text-white');
        } else {
            $(this).parent().removeClass('bg-success text-white');
        }

        // Cập nhật tổng tiền
        updateTotalAmount();
    });

    function updateTotalAmount() {
        var total = 0;
        $('input[name="seatIds"]:checked').each(function() {
            total += parseFloat($(this).data('price'));
        });
        $('#totalAmount').text(total.toFixed(2));
        $('#totalAmountInput').val(total.toFixed(2));
        $('#finalAmount').text(total.toFixed(2));
        $('#finalAmountInput').val(total.toFixed(2));
    }
});
