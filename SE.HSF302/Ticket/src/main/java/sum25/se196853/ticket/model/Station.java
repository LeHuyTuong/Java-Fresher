package sum25.se196853.ticket.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    private String name;
    private String city;
    private String province;

    @Column(name = "km_from_start")
    private BigDecimal kmFromStart;

    @Enumerated(EnumType.STRING)
    private StationType type;

    @Enumerated(EnumType.STRING)
    private StationStatus status;

    public enum StationType {
        start, middle, end
    }

    public enum StationStatus {
        active, inactive
    }
}
