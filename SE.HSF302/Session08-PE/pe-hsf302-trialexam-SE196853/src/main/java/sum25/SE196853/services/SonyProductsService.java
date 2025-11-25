package sum25.SE196853.services;

// ... các import ...
import sum25.SE196853.entity.SonyProducts;

import java.util.List;

public interface SonyProductsService {
    SonyProducts createSonyProducts(SonyProducts ss);
    void update(Long id, SonyProducts ss);
    SonyProducts findById(Long id);
    void delete(Long id);
    List<SonyProducts> findAll();

}
