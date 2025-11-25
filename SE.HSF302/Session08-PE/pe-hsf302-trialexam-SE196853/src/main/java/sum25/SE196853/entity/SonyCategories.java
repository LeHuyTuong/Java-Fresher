package sum25.SE196853.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "SonyCategories")
@NoArgsConstructor
@AllArgsConstructor
public class SonyCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CateID",   nullable = false)
    private Integer cateID;
    @Column(name = "CateName",   nullable = false, length = 50)
    private String cateName;
    @Column(name = "status" ,  nullable = false, length = 10)
    private String status;
}
