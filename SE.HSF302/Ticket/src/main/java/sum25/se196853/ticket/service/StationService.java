package sum25.se196853.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se196853.ticket.model.Station;
import sum25.se196853.ticket.repository.StationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    public List<Station> findActiveStations() {
        return stationRepository.findByStatus(Station.StationStatus.active);
    }

    public Optional<Station> findById(Integer id) {
        return stationRepository.findById(id);
    }

    public Station findByCode(String code) {
        return stationRepository.findByCode(code);
    }

    public Station save(Station station) {
        return stationRepository.save(station);
    }

    public void deleteById(Integer id) {
        stationRepository.deleteById(id);
    }
}
