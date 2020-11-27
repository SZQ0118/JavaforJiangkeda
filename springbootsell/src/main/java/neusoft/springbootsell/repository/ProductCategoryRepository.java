package neusoft.springbootsell.repository;

import neusoft.springbootsell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{
   //categoryType
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);
}
