package sum25.se196853.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se196853.ticket.model.Trip;
import sum25.se196853.ticket.repository.TripRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    public Optional<Trip> findById(Integer id) {
        return tripRepository.findById(id);
    }

    public List<Trip> findByRouteAndDate(String routeCode, LocalDate date) {
        return tripRepository.findByRouteCodeAndTripDateAndStatus(routeCode, date, Trip.TripStatus.active);
    }

    public Trip save(Trip trip) {
        return tripRepository.save(trip);
    }

    public void deleteById(Integer id) {
        tripRepository.deleteById(id);
    }
}
