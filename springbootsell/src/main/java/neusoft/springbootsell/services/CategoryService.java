package neusoft.springbootsell.services;

import neusoft.springbootsell.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    public ProductCategory findOne(Integer categoryId);

    public List<ProductCategory> findAll();

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryId);

    public ProductCategory save(ProductCategory productCategory);

}
