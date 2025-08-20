package com.tuonglh.coffee.coffee.controller;


//CRUD Product . xử lý request response
// phụ trasch url đến CRUD product
// chuyển sang service và repo
//CLASS NAY LANG NGHE CAC URL, VA XEM URL NAO PHU HOP VOI HAM TRONG CLASS NAY
//THÌ GỌI HÀM DO - METHOD MAPPING VOI URL !!!
//2 VIỆC: NÓ PHẢI LANG NGHE URL, NGHE XONG, GỌI DÚNG HÀM ỨNG VỚI URL
//VIỆC 0: NO PHẢI LA 1 BEAN DC NEW TU DONG, VAO RAM VA NGHE
//VIEC 1: NGHE - MAY LA @CONTROLLER: BEAN VA LANG NGHE
//VIỆC 2: HÀM NÀO ỨNG VỚI URL NÀO; TRẢ VỀ HTML !!!
//QUAN TRONG: 1 URL GET -> ỨNG VOI 1 HÀM return 1 trang nào đó
//                          Hàm nằm trong 1 class @Controller
//                        ->Get là lấy 1 trang thông tin

import com.tuonglh.coffee.coffee.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller // @RestController ,cũng nghe,  nhưng trả về Json
public class ProductController {

    @RequestMapping("/getList")
    public String list(Model model){ // show danh sach san pham
        model.addAttribute("msg" , "Welcome to Coffee");

        //chuaanr bi 1 danh sach show
        // hard-code test thu, thuc te goi service, repo , tiem chich tu dong ko can new
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Coffee" , "Hehe", 20_222));
        productList.add(new Product("hehe" , "ngon vai", 22_222));

        model.addAttribute("productList" , productList);


        return "products"; // return teen trang , view ko caanf ,htmll
        // tự thymeleaf depen nó lo gắn tên
    }
}
