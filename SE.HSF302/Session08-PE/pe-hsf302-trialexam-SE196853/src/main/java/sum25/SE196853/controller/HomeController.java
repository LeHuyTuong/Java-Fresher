package sum25.SE196853.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sum25.SE196853.entity.SonyAccounts;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(HttpSession session) {
        SonyAccounts account = (SonyAccounts) session.getAttribute("user");
        if(account == null){
            return "redirect:/login";
        } else if(account.getRoleId() == 1 || account.getRoleId() == 2){
            return "redirect:/product/list";
        } else {
            return "home";
        }
    }

    @GetMapping("/403")
    public String accessDenied(){
        return "403";
    }
}
