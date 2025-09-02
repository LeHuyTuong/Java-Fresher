package com.tuonglh.coffee.samplecode.controller;

import com.tuonglh.coffee.samplecode.configuration.Translator;
import com.tuonglh.coffee.samplecode.dto.request.SampleDTO;
import com.tuonglh.coffee.samplecode.dto.request.UserRequestDTO;
import com.tuonglh.coffee.samplecode.dto.response.ResponseData;
import com.tuonglh.coffee.samplecode.dto.response.ResponseError;
import com.tuonglh.coffee.samplecode.dto.response.ResponseSuccess;
import com.tuonglh.coffee.samplecode.dto.response.UserDetailResponse;
import com.tuonglh.coffee.samplecode.dto.validation.enums.UserStatus;
import com.tuonglh.coffee.samplecode.exception.ResourceNotFoundException;
import com.tuonglh.coffee.samplecode.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // cho class có nghĩa là các RESTful API của chúng ta được trả về kết quả dưới dạng JSON hoặc XML
// và chúng ta không cần gán thêm @ResponseBody cho các method.
@RequestMapping("/user") // mapping url
@Validated
@Slf4j
@RequiredArgsConstructor
@Tag(name = "User Controller")  // đặt tên cho controller
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
    private final UserService userService;

//    @PostMapping(value="/")
//    public ResponseData<Integer> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
//        System.out.println("Request createUser" + userRequestDTO.getFirstName());
//        SampleDTO sampleDTO = SampleDTO.builder()
//                .id(1)
//                .name("Tuong")
//                .build();
//        log.info("Request createUser" + userRequestDTO.getFirstName());
//        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("user.add.success"), 1 );
//    }

    @Operation(summary = "Add user", description = "API create new user")
    @PostMapping(value = "/{userId}" )
    //@RequestMapping(method = RequestMethod.POST, path = "/" , headers = "apiKey=v1.0")
    public ResponseData<Integer> addUser(@Valid @RequestBody UserRequestDTO userDTO){
        log.info("Request add user, {}, {}", userDTO.getFirstName(), userDTO.getLastName());
         log.info("Request add userFirstName:  {}" , userDTO.getFirstName());
         //return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Can not create user ");
         try{
             long UserId = userService.saveUser(userDTO);
             return new ResponseData<>(HttpStatus.CREATED.value(),Translator.toLocale("user.add.success"), 1 );
         }catch(ResourceNotFoundException e){
             log.error("errorMessage = {}", e.getMessage(), e.getCause());
             return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Save failed ");
         }
    }

    @Operation(summary = "Update all user", description = "API Update user")
    @PutMapping("/{userId}")// sửa toàn bộ
    public ResponseData<Integer> updateUser(@PathVariable long userId, @Valid @RequestBody UserRequestDTO userDTO){
        log.info("Request update usedId= {}" , userId);
        try{
            userService.updateUser(userId, userDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), Translator.toLocale("user.update.success"), 1);

        }catch(Exception e){
            log.info("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update failed ");
        }

    }

    @Operation(summary = "Update status", description = "API update status")
    @PatchMapping("/{userId}")
    public ResponseData<Integer> changeUser(@PathVariable long userId, @RequestParam UserStatus status){  // request param bắt buọc truyền status
                            //Lấy giá trị từ path trong URL.
        log.info("Request change status, userId= {}" , status);
        try{
            userService.changeStatus(userId, status);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User successfully updated", 1);
        }catch(Exception e){
            log.info("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Change status failed ");
        }

    }

    @Operation(summary = "Delete user", description = "API delete user")
    @DeleteMapping("/{userId}")
    public ResponseData<Integer> deleteUser(@PathVariable @Min(value = 1, message = "userId must be greater than 0") int userId) {
        log.info("Request delete user, userId= {}" , userId);

        try{
            userService.deleteUser(userId);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User successfully deleted", 1);
        }catch(Exception e){
            log.info("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete user failed ");
        }
    }

    @Operation(summary = "Get user", description = "API get  user")
    @GetMapping("/{userId}")
    public ResponseData<UserDetailResponse> getUser(@PathVariable long userId){
        log.info("Request get user, userId= {} " , userId);
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "user", userService.getUser(userId));
        }catch(ResourceNotFoundException e){
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }

    @Operation(summary = "Update user", description = "API Update user")
    @PatchMapping("/{userId}/status")
    public ResponseData<Integer> updateStatus(@Min(10) @PathVariable int userId, @RequestParam boolean status) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User successfully updated", 1);
    }

    @Operation(summary = "Get list of user per pageNo", description = "send a request via this API to get user list by pageNo and pageSize")
    @GetMapping("/list")
    public ResponseData<?> getUserList(
            @RequestParam (required = false) String email,
            @RequestParam(defaultValue = "0") int pageNo,
            @Min(10) @RequestParam (defaultValue = "20") int pageSize,
            @RequestParam(required = false) String sortBy){

        log.info("Request get user list");
        return new ResponseData<>(HttpStatus.OK.value(), "List of user", userService.getAllUsersWithSortBy(pageNo,pageSize, sortBy));
    }

    @Operation(summary = "Get list of all user ort by multiple colum ", description = "send a request via this API to get user list by sort")
    @GetMapping("/list-with-sort-by-multiple-columns")
    public ResponseData<?> getAllUsersWithSortByMultipleColumns(
            @RequestParam (required = false) String email,
            @RequestParam(defaultValue = "0") int pageNo,
            @Min(10) @RequestParam (defaultValue = "20") int pageSize,
            @RequestParam(value = "sort", required = false) String... sort){

        log.info("Request get all users with sort by multiple column");
        return new ResponseData<>(HttpStatus.OK.value(), "List of user", userService.getAllUsersWithSortByMultipleColumns(pageNo,pageSize, sort));
    }

    @Operation(summary = "Get list of all user ort by multiple colum and search ", description = "send a request via this API to get user list by sort")
    @GetMapping("/list-with-sort-by-multiple-columns-search")
    public ResponseData<?> getAllUsersWithSortByMultipleColumnsAndSearch(
            @RequestParam (required = false) String email,
            @RequestParam(defaultValue = "0") int pageNo,
            @Min(10) @RequestParam (defaultValue = "20") int pageSize,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "sort", required = false) String sortsBy){

        log.info("Request get all users with sort by multiple column and search");
        return new ResponseData<>(HttpStatus.OK.value(), "List of user", userService.getAllUsersWithSortByMultipleColumnsAndSearch(pageNo,pageSize,search,sortsBy));
    }

}
