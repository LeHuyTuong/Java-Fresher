package sum25.SE196853.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.SE196853.entity.SonyProducts;
import sum25.SE196853.repository.SonyProductsRepository;
import sum25.SE196853.services.SonyProductsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SonyProductsServiceImpl implements SonyProductsService {

    @Autowired
    private SonyProductsRepository productsRepository;

    @Override
    public List<SonyProducts> findAll() {
        // Task 8: Lấy danh sách đã sắp xếp
        return productsRepository.findAllByOrderByProductIDDesc();
    }

    @Override
    public SonyProducts createSonyProducts(SonyProducts ss) {
        // Task 8: Set ngày hiện tại khi tạo mới
        ss.setCreateAt(LocalDate.now());
        return productsRepository.save(ss);
    }

    @Override
    public void update(Long id, SonyProducts ss) {
        SonyProducts product = productsRepository.findByProductID(id);
        if (product != null) {
            product.setProductName(ss.getProductName());
            product.setPrice(ss.getPrice());
            product.setStock(ss.getStock());
            product.setCateID(ss.getCateID());
            // Không cập nhật createAt khi update
            productsRepository.save(product);
        }
    }

    @Override
    public void delete(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public SonyProducts findById(Long id) {
        return productsRepository.findByProductID(id);
    }
}
