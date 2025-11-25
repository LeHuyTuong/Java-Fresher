package sum25.SE196853.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "SonyAccounts")
@NoArgsConstructor
@AllArgsConstructor
public class SonyAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id",  nullable = false)
    private int accountID;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Column(name = "phone", length = 13)
    private String phone;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Column(name = "password", length = 10)
    private String password;
    @Column(name = "roleID")
    private int roleId;

}
