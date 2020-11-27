package neusoft.springbootsell.services.Impl;

import neusoft.springbootsell.dataobject.ProductInfo;
import neusoft.springbootsell.repository.ProductInfoRepository;
import neusoft.springbootsell.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatusIs(0);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
       return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(String productId) {
        ProductInfo pro = repository.findOne(productId);
        pro.setProductStatus(0);
        repository.save(pro);
    }

    @Override
    public void decreaseStock(String productId) {
        ProductInfo pro = repository.findOne(productId);
        pro.setProductStatus(1);
        repository.save(pro);
    }
}
