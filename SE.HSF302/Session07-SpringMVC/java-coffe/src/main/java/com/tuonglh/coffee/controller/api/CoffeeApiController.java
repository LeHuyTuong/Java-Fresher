package com.tuonglh.coffee.controller.api;

import com.tuonglh.coffee.entity.Coffee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
// phục vụ các url mà muốn lấy data thô, trả về json
// hoặc nhân json từ trang web gửi lên
// url api tách hẳn so với url web
// http: /api/v1/..
@RequestMapping("api/v1")
public class CoffeeApiController {
    @GetMapping("/cup")
    public Coffee getACup(){
        return new Coffee("JSNT", "Cafe Java topping", 29_000); // Lấy từ DB
        //qua Serice. response . jpa-hibernate.jdbc
    } //gọi Jackson convert từ object thành JSON bên trình duyệt
//data thôi - Web API: gọi hàm qua URL trả về JSON
//Swagger UI: help, document chứa url để dùng thử API

    // gửi data lên server , JSon gửi lên, xử lý
    @PostMapping("/addCup")
    public Coffee addACup(@RequestBody Coffee cup){
        // sửa object đã nhân xong trả về
        String oldName = cup.getName();
        String newName = oldName = oldName + " | Haizz ";
        cup.setName(newName);
        return cup;
    }
}
