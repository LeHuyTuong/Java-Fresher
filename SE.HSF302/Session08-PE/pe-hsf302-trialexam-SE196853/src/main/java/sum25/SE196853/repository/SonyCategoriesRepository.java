package sum25.SE196853.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.SE196853.entity.SonyCategories;

@Repository
public interface SonyCategoriesRepository extends JpaRepository<SonyCategories, Integer> {
        SonyCategories findByCateID(Integer id);
}
