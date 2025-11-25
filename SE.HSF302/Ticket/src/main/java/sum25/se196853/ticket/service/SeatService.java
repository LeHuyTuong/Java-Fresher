package sum25.se196853.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se196853.ticket.model.Seat;
import sum25.se196853.ticket.repository.SeatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    public Optional<Seat> findById(Integer id) {
        return seatRepository.findById(id);
    }

    public List<Seat> findByTrainCodeAndIsActiveTrue(String trainCode) {
        return seatRepository.findByTrainCodeAndIsActiveTrue(trainCode);
    }

    public List<Seat> findByTrainCodeAndCarriageNumber(String trainCode, Integer carriageNumber) {
        return seatRepository.findByTrainCodeAndCarriageNumber(trainCode, carriageNumber);
    }

    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    public void deleteById(Integer id) {
        seatRepository.deleteById(id);
    }

    // Phương thức bổ sung để kiểm tra ghế đã được đặt cho một chuyến đi chưa
    public boolean isSeatAvailableForTrip(Integer seatId, Integer tripId) {
        // Triển khai logic kiểm tra xem ghế đã được đặt trong chuyến đi này chưa
        // Thực tế cần truy vấn vào bảng tickets để kiểm tra
        return true; // Giả sử tất cả ghế đều khả dụng
    }

    // Phương thức tìm tất cả các ghế khả dụng cho một chuyến đi
    public List<Seat> findAvailableSeatsForTrip(Integer tripId, String trainCode) {
        // Đây là triển khai đơn giản, trả về tất cả ghế hoạt động của tàu
        // Thực tế cần kiểm tra thêm những ghế nào đã được đặt trong chuyến đi
        return findByTrainCodeAndIsActiveTrue(trainCode);
    }
}
