package com.tuonglh.coffee.samplecode.controller;

import com.tuonglh.coffee.samplecode.dto.request.UserRequestDTO;
import com.tuonglh.coffee.samplecode.dto.response.ResponseData;
import com.tuonglh.coffee.samplecode.dto.response.ResponseError;
import com.tuonglh.coffee.samplecode.dto.response.ResponseSuccess;
import com.tuonglh.coffee.samplecode.exception.ResourceNotFoundException;
import com.tuonglh.coffee.samplecode.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // cho class có nghĩa là các RESTful API của chúng ta được trả về kết quả dưới dạng JSON hoặc XML
// và chúng ta không cần gán thêm @ResponseBody cho các method.
@RequestMapping("/user") // mapping url
@Validated
public class UserController {


    /** @Operation(summary = "sumary", description = "description", responses = {
            @ApiResponse(responseCode = "201", description = "User added successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(name = "ex name", summary = "ex summary"
                                    , value = """
                                    {
                                      "status": 204,
                                      "message": "User successfully updated",
                                      "data": 1
                                    }
                                    """
                            )
                    )
            )
    }) */  // mô tả api

    @Autowired
    private UserService userService;

     @PostMapping(value = "/{userId}" )
    //@RequestMapping(method = RequestMethod.POST, path = "/" , headers = "apiKey=v1.0")
    public ResponseData<Integer> addUser(@Valid @RequestBody UserRequestDTO userDTO){
         System.out.println("Request add user" + userDTO.getFirstName());
         //return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Can not create user ");
         try{
             userService.addUser(userDTO);
             return new ResponseData<>(HttpStatus.CREATED.value(),"User add successfully", 1 );
         }catch(ResourceNotFoundException e){
             return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Save failed ");
         }
    }

    @PutMapping("/{userId}")// sửa toàn bộ
    public ResponseData<Integer> updateUser(@PathVariable int userId, @Valid @RequestBody UserRequestDTO userDTO){
        System.out.println("Request update usedId= " + userId);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User successfully updated", 1);
    }

    @PatchMapping("/{userId}")
    public ResponseData<Integer> changeUser(@PathVariable int userId, @RequestParam boolean status){  // request param bắt buọc truyền status
                            //Lấy giá trị từ path trong URL.
        System.out.println("Request change status, userId= " + status);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User successfully updated", 1);
    }

    @DeleteMapping("/{userId}")
    public ResponseData<Integer> deleteUser(@PathVariable @Min(value = 1, message = "userId must be greater than 0") int userId) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User successfully deleted", 1);
    }

    @GetMapping("/{userId}")
    public ResponseData<UserRequestDTO> getUser(@PathVariable int userId){
        System.out.println("Request get user, userId= " + userId);
        return new ResponseData<>(HttpStatus.OK.value(), "user", new  UserRequestDTO("Tuong", "Hhehe", "phone", "email"));
    }

    @PatchMapping("/{userId}/status")
    public ResponseData<Integer> updateStatus(@Min(10) @PathVariable int userId, @RequestParam boolean status) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User successfully updated", 1);
    }

    @GetMapping("/list")
    public ResponseData<List<UserRequestDTO>> getUserList(
            @RequestParam (required = false) String email,
            @RequestParam(defaultValue = "0") int pageNo,
            @Min(10) @RequestParam (defaultValue = "20") int pageSize){
        System.out.println("Request get user list");
        return new ResponseData<>(HttpStatus.OK.value(), "List of user", List.of(new UserRequestDTO("Tuong", "Hhehe", "phone", "email")
        , new UserRequestDTO("Tuondasdg", "zxc", "phovzxne", "emailzxc")));
    }
}
