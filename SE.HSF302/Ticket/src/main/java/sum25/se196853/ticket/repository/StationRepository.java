package sum25.se196853.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.se196853.ticket.model.Station;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    List<Station> findByStatus(Station.StationStatus status);
    Station findByCode(String code);
}
