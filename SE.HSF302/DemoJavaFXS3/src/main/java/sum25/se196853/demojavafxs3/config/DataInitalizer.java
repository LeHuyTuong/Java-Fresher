package sum25.se196853.demojavafxs3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sum25.se196853.demojavafxs3.entity.User;
import sum25.se196853.demojavafxs3.service.UserService;

@Component
public class DataInitalizer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra xem user 'admin' đã tồn tại chưa
        if(userService.getUser("admin", "@1") != null){
            System.out.println("Data already initialized.");
            return;
        }
        else{
            System.out.println("Initializing user data...");
            User user = new User();
            user.setUsername("admin");
            user.setPassword("@1");
            userService.createUser(user); // <-- Phải lưu user

            User user1 = new User();
            user1.setUsername("staff");
            user1.setPassword("@2");
            userService.createUser(user1); // <-- Phải lưu user

            User user2 = new User();
            user2.setUsername("member"); // <-- Sửa user1 thành user2
            user2.setPassword("@3");     // <-- Sửa user1 thành user2
            userService.createUser(user2); // <-- Phải lưu user

            System.out.println("Data initialized successfully.");
        }
    }
}
