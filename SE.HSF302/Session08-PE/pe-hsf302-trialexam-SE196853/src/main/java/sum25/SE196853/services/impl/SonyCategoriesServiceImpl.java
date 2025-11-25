package sum25.SE196853.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.SE196853.entity.SonyCategories;
import sum25.SE196853.entity.SonyProducts;
import sum25.SE196853.repository.SonyCategoriesRepository;
import sum25.SE196853.services.SonyCategoriesService;

import java.util.List;

@Service
public class SonyCategoriesServiceImpl implements SonyCategoriesService {

    @Autowired
    private SonyCategoriesRepository sonyCategoriesRepository;

    @Override
    public SonyCategories createSonyCategories(SonyCategories ss) {
        return sonyCategoriesRepository.save(ss);
    }

    @Override
    public void update(Integer id, SonyCategories ss) {
        SonyCategories existCate = sonyCategoriesRepository.findByCateID(id);
        if(existCate!=null){
            existCate.setCateName(ss.getCateName());
            existCate.setStatus(ss.getStatus());
            sonyCategoriesRepository.save(existCate);
        }
    }

    @Override
    public List<SonyCategories> getAll() {
        return sonyCategoriesRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        sonyCategoriesRepository.deleteById(id);
    }
}
