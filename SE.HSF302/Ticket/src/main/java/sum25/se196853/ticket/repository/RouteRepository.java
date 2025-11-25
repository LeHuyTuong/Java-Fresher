package sum25.se196853.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.se196853.ticket.model.Route;
import sum25.se196853.ticket.model.Station;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    List<Route> findByStartStationAndEndStation(Station startStation, Station endStation);
    List<Route> findByStatus(Route.RouteStatus status);
}
