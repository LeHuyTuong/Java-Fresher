package sum25.SE196853.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sum25.SE196853.entity.SonyAccounts;
import sum25.SE196853.entity.SonyCategories;
import sum25.SE196853.entity.SonyProducts;
import sum25.SE196853.services.SonyCategoriesService;
import sum25.SE196853.services.SonyProductsService;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private SonyProductsService productsService;
    @Autowired
    private SonyCategoriesService categoriesService;

    @GetMapping("/product/list")
    public String productList(Model model, HttpSession session){
        SonyAccounts account = (SonyAccounts) session.getAttribute("user");
        if(account == null || (account.getRoleId() != 1 && account.getRoleId() != 2)) {
            return "redirect:/login?error=true";
        }

        List<SonyProducts> products = productsService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("account", account);
        return "product/list";
    }

    // Task 8: Show form to add a new product (Admin only)
    @GetMapping("/product/create")
    public String showCreateForm(Model model, HttpSession session){
        SonyAccounts account = (SonyAccounts) session.getAttribute("user");
        if(account == null || account.getRoleId() != 1) {
            return "redirect:/product/list";
        }

        model.addAttribute("product", new SonyProducts());
        model.addAttribute("categories", categoriesService.getAll());
        return "product/form";
    }

    @PostMapping("/product/create")
    public String createProduct(@Valid @ModelAttribute("product") SonyProducts product,
                                BindingResult bindingResult, Model model, HttpSession session){
        SonyAccounts account = (SonyAccounts) session.getAttribute("user");
        if(account == null || account.getRoleId() != 1) {
            return "redirect:/product/list";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoriesService.getAll());
            model.addAttribute("stock", productsService.findAll());
            return "product/form";
        }
        productsService.createSonyProducts(product);
        return "redirect:/product/list";
    }

    @GetMapping("/product/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model, HttpSession session){
        SonyAccounts account = (SonyAccounts) session.getAttribute("user");
        if(account == null || account.getRoleId() != 1) {
            return "redirect:/product/list";
        }

        SonyProducts product = productsService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoriesService.getAll());
        return "product/form";
    }

    // Task 9: Process updating a product
    @PostMapping("/product/update")
    public String updateProduct(@Valid @ModelAttribute("product") SonyProducts product,
                                BindingResult bindingResult, Model model, HttpSession session) {
        SonyAccounts account = (SonyAccounts) session.getAttribute("user");
        if(account == null || account.getRoleId() != 1) {
            return "redirect:/product/list";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoriesService.getAll());
            return "product/form";
        }
        productsService.update(product.getProductID(), product);
        return "redirect:/product/list";
    }


    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, HttpSession session) {
        SonyAccounts account = (SonyAccounts) session.getAttribute("user");
        if(account == null || account.getRoleId() != 1) {
            return "redirect:/product/list";
        }
        productsService.delete(id);
        return "redirect:/product/list";
    }
}
