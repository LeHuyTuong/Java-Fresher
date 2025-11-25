package sum25.se196853.ticket.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "train_code")
    private String trainCode;

    @Column(name = "route_code")
    private String routeCode;

    @Column(name = "trip_date")
    private LocalDate tripDate;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Enumerated(EnumType.STRING)
    private TripStatus status;

    public enum TripStatus {
        active, cancelled
    }
}
