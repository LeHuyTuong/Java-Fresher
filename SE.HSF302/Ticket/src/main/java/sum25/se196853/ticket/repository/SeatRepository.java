package sum25.se196853.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.se196853.ticket.model.Seat;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByTrainCodeAndIsActiveTrue(String trainCode);
    List<Seat> findByTrainCodeAndCarriageNumber(String trainCode, Integer carriageNumber);
}
