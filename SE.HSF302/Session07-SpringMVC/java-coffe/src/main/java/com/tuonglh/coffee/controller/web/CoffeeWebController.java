package com.tuonglh.coffee.controller.web;

import com.tuonglh.coffee.entity.Coffee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller// 1 bean đc new từ đầu bởi IoC contriner, chuyên trả lời các http request đến từ url của trình duyệt
// có nhiều url khc nhau thì ứng với nhiều hàm trong class này
// các hàm trả về html
public class CoffeeWebController {

    @GetMapping("/") // user go localhost:6969 nghĩa là nhảy đến hàm này
    public String home(Model model){

        model.addAttribute("cup1","Cafe sữa ");
        model.addAttribute("cup2", new Coffee("JS", "Hehe", 29_000));
        return "index.html"; // trả về trang cho trình duyệt
    }

}
