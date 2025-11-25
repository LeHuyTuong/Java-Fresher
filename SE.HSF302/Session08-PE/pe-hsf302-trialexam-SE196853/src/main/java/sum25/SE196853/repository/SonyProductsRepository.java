package sum25.SE196853.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.SE196853.entity.SonyProducts;
import java.util.List;

@Repository
public interface SonyProductsRepository extends JpaRepository<SonyProducts, Long> {
    SonyProducts findByProductID(Long id);

    // Task 8: Sắp xếp để item mới nhất lên đầu
    List<SonyProducts> findAllByOrderByProductIDDesc();
}
