package sum25.SE196853.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sum25.SE196853.entity.SonyAccounts;
import sum25.SE196853.services.impl.SonyAccountsServiceImpl;

@Controller
public class LoginController {
    @Autowired
    private SonyAccountsServiceImpl service;

    @GetMapping("/")
    public String loginPage(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("accountForm", new SonyAccounts());
        return "login";
    }


    @PostMapping("/login")
    public String loginProcess(@Valid @ModelAttribute("accountForm") SonyAccounts accountForm,
                               BindingResult bindingResult,
                               Model model,
                               HttpSession session){

        if (bindingResult.hasErrors()) {
            return "login";
        }

        SonyAccounts account =  service.getUser(accountForm.getPhone(), accountForm.getPassword());

        if (account == null) {
            model.addAttribute("loginError", "Sai thông tin đăng nhập hoặc bạn không có quyền!");
            return "login";
        }
        session.setAttribute("user", account);
        if(account.getRoleId() == 1 || account.getRoleId() == 2){
            return "redirect:/product/list";
        }
        else{
            return "redirect:/home";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}


