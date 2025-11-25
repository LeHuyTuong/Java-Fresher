package sum25.se196853.ticket.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "from_station_id")
    private Station fromStation;

    @ManyToOne
    @JoinColumn(name = "to_station_id")
    private Station toStation;

    @Column(name = "from_order")
    private Integer fromOrder;

    @Column(name = "to_order")
    private Integer toOrder;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "passenger_phone")
    private String passengerPhone;

    @Column(name = "passenger_id_card")
    private String passengerIdCard;

    @Column(name = "distance_km")
    private BigDecimal distanceKm;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(name = "booked_at")
    private LocalDateTime bookedAt;

    @Column(name = "checked_in_at")
    private LocalDateTime checkedInAt;

    public enum TicketStatus {
        booked, paid, checked_in, used, cancelled
    }
}
