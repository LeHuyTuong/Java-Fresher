package sum25.se196853.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se196853.ticket.model.Route;
import sum25.se196853.ticket.model.Station;
import sum25.se196853.ticket.repository.RouteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public List<Route> findActiveRoutes() {
        return routeRepository.findByStatus(Route.RouteStatus.active);
    }

    public Optional<Route> findById(Integer id) {
        return routeRepository.findById(id);
    }

    public List<Route> findByStartAndEndStation(Station start, Station end) {
        return routeRepository.findByStartStationAndEndStation(start, end);
    }

    public Route save(Route route) {
        return routeRepository.save(route);
    }

    public void deleteById(Integer id) {
        routeRepository.deleteById(id);
    }
}
