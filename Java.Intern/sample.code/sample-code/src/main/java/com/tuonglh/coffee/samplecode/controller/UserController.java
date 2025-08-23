package com.tuonglh.coffee.samplecode.controller;

import com.tuonglh.coffee.samplecode.dto.request.UserRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // cho class có nghĩa là các RESTful API của chúng ta được trả về kết quả dưới dạng JSON hoặc XML
// và chúng ta không cần gán thêm @ResponseBody cho các method.
@RequestMapping("/user") // mapping url
@Validated
public class UserController {


    // @PostMapping(value = "/" , headers = "apiKey=v1.0)
    @RequestMapping(method = RequestMethod.POST, path = "/" , headers = "apiKey=v1.0")
    public String addUser(@Valid @RequestBody UserRequestDTO userDTO){
        return "add user";
    }

    @PutMapping("/{userId}")// sửa toàn bộ
    public String updateUser(@PathVariable int userId, @Valid @RequestBody UserRequestDTO userDTO){
        System.out.println("Request update usedId= " + userId);
        return "update user";
    }

    @PatchMapping("/{userId}")
    public String changeUser(@PathVariable int userId, @RequestParam boolean status){  // request param bắt buọc truyền status
                            //Lấy giá trị từ path trong URL.
        System.out.println("Request change status, userId= " + status);
        return "change user";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable @Min(value = 1, message = "userId must be greater than 0") int userId) {
        return "User deleted";
    }

    @GetMapping("/{userId}")
    public UserRequestDTO getUser(@PathVariable int userId){
        System.out.println("Request get user, userId= " + userId);
        return new UserRequestDTO("Tuong", "Hhehe", "phone", "email");
    }

    @PatchMapping("/{userId}/status")
    public String updateStatus(@Min(1) @PathVariable int userId, @RequestParam boolean status) {
        return "User Status changed";
    }

    @GetMapping("/list")
    public List<UserRequestDTO> getUserList(
            @RequestParam (required = false) String email,
            @RequestParam(defaultValue = "10") int pageNo,
            @RequestParam (defaultValue = "10") int pageSize){
        System.out.println("Request get user list");
        return List.of(new UserRequestDTO("Tuong", "Hhehe", "phone", "email")
        , new UserRequestDTO("Tuondasdg", "zxc", "phovzxne", "emailzxc"));
    }
}
