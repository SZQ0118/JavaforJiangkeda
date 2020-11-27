package neusoft.springbootsell.services.Impl;

import neusoft.springbootsell.dataobject.ProductCategory;
import neusoft.springbootsell.repository.ProductCategoryRepository;
import neusoft.springbootsell.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository repository;
    @Override
    public ProductCategory findOne(Integer categoryId) {

        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {

        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryId) {
        return repository.findByCategoryTypeIn(categoryId);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
