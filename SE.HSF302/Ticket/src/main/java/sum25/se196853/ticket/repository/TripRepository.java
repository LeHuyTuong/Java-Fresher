package sum25.se196853.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.se196853.ticket.model.Trip;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByRouteCodeAndTripDateAndStatus(String routeCode, LocalDate tripDate, Trip.TripStatus status);
}
