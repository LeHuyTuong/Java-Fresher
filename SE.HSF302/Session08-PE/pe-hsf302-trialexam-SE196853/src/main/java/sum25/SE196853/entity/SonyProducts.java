package sum25.SE196853.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SonyProducts")
public class SonyProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private long productID;

    @NotBlank(message = "Product name is required")
    @Size(min = 5, max = 50, message = "Product name must be between 5 and 50 characters")
    private String productName;

    @Min(value = 100, message = "Price must be greater than or equal to 100")
    @NotNull(message = "Price can not null")
    private int price;

    @Min(value = 0, message = "Stock must be between 0 and 1000")
    @Max(value = 1000, message = "Stock must be between 0 and 1000")
    @NotNull(message = "Stock can not null")
    private int stock;

    private LocalDate createAt;

    @NotNull(message = "Must choice a Category")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CateID")
    private SonyCategories cateID;
}
