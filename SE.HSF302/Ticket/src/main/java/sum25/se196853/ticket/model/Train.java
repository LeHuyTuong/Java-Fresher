package sum25.se196853.ticket.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "total_carriages")
    private Integer totalCarriages;

    @Column(name = "seat_capacity")
    private Integer seatCapacity;
}
