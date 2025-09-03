package com.tuonglh.coffee.samplecode.dto.response;

import com.tuonglh.coffee.samplecode.dto.validation.annotation.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
@Getter
@Builder
public class UserDetailResponse implements Serializable {
    private Long id;

    /*
     * Long là wrapper class của kiểu nguyên thủy long.
     * Dùng Long khi CẦN null (ví dụ id của entity trước khi persist).
     * Nếu luôn có giá trị -> ưu tiên long (ít tốn bộ nhớ, không boxing/unboxing).
     *
     * Lưu ý về bộ nhớ:
     * - Field (thuộc tính) của cả long lẫn Long đều nằm trên HEAP (vì thuộc về object).
     * - Long là một OBJECT riêng: tốn thêm overhead và phát sinh GC.
     * - "long ở stack, Long ở heap" là cách nói đơn giản hóa cho biến cục bộ; với field thì không đúng.
     *
     * Cạm bẫy:
     * - Auto-unboxing có thể NPE: long x = dto.getId(); // getId() == null -> NPE
     * - So sánh Long dùng equals(), đừng dùng == (== so sánh tham chiếu).
     */
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public UserDetailResponse(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public UserDetailResponse(Long id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

}
