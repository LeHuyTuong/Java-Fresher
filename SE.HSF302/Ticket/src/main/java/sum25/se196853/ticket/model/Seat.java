package sum25.se196853.ticket.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "train_code")
    private String trainCode;

    @Column(name = "carriage_number")
    private Integer carriageNumber;

    @Column(name = "seat_number")
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type")
    private SeatType seatType;

    @Column(name = "price_per_km")
    private BigDecimal pricePerKm;

    @Column(name = "price_factor")
    private BigDecimal priceFactor;

    @Column(name = "is_active")
    private Boolean isActive;

    public enum SeatType {
        seat, sleeper, vip
    }
}
