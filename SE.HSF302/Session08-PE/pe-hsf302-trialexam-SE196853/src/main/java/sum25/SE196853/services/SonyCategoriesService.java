package sum25.SE196853.services;

import sum25.SE196853.entity.SonyCategories;
import sum25.SE196853.entity.SonyProducts;

import java.util.List;

public interface SonyCategoriesService {

    SonyCategories createSonyCategories(SonyCategories ss);
    void update(Integer id, SonyCategories ss);
    List<SonyCategories> getAll();
    void delete(Integer id);
}
