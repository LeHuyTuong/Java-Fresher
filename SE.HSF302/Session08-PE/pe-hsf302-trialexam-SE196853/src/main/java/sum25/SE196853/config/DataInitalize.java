package sum25.SE196853.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sum25.SE196853.entity.SonyAccounts;
import sum25.SE196853.entity.SonyCategories;
import sum25.SE196853.entity.SonyProducts;
import sum25.SE196853.services.SonyCategoriesService; // Thêm import
import sum25.SE196853.services.SonyProductsService;
import sum25.SE196853.services.impl.SonyAccountsServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInitalize implements CommandLineRunner {

    @Autowired
    private SonyAccountsServiceImpl service;

    // Thêm service cho Categories
    @Autowired
    private SonyCategoriesService categoriesService;
    @Autowired
    private SonyProductsService sonyProductsService;

    @Override
    public void run(String... args) throws Exception {
        SonyAccounts s1 =new SonyAccounts();
        s1.setPassword("@1");
        s1.setRoleId(1);
        s1.setPhone("0905111111");
        service.create(s1);

        SonyAccounts s2 =new SonyAccounts();
        s2.setPassword("@1");
        s2.setRoleId(2);
        s2.setPhone("0905222222");
        service.create(s2);

        SonyAccounts s3 =new SonyAccounts();
        s3.setPassword("@1");
        s3.setRoleId(3);
        s3.setPhone("0905333333");
        service.create(s3);

        SonyCategories c1 =new SonyCategories();
        c1.setCateName("HeadPhone");
        c1.setStatus("active");
        categoriesService.createSonyCategories(c1);

        SonyCategories c2 =new SonyCategories();
        c2.setCateName("Camera");
        c2.setStatus("active");
        categoriesService.createSonyCategories(c2);

        SonyCategories c3 =new SonyCategories();
        c3.setCateName("TVs");
        c3.setStatus("active");
        categoriesService.createSonyCategories(c3);


        SonyProducts p1 = new SonyProducts();
        p1.setProductName("Alpha 1 II - Full-frame Mirrorless");
        p1.setPrice(6000);
        p1.setStock(3);
        p1.setCreateAt(LocalDate.parse("2025-03-03"));
        p1.setCateID(c2);
        sonyProductsService.createSonyProducts(p1);

        SonyProducts p2 = new SonyProducts();
        p2.setProductName("Alpha 7C II – Full-frame");
        p2.setPrice(2000);
        p2.setStock(5);
        p2.setCreateAt(LocalDate.parse("2025-04-04"));
        p2.setCateID(c2);
        sonyProductsService.createSonyProducts(p2);

        SonyProducts p3 = new SonyProducts();
        p3.setProductName("BRAVIA 8 OLED 4K HDR TV");
        p3.setPrice(2500);
        p3.setStock(10);
        p3.setCreateAt(LocalDate.parse("2025-01-01"));
        p3.setCateID(c3);
        sonyProductsService.createSonyProducts(p3);

        SonyProducts p4 = new SonyProducts();
        p4.setProductName("LinkBuds Fit Truly Wireless Noise Canceling");
        p4.setPrice(180);
        p4.setStock(15);
        p4.setCreateAt(LocalDate.parse("2025-03-03"));
        p4.setCateID(c1);
        sonyProductsService.createSonyProducts(p4);
    }
}
