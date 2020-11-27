package neusoft.springbootsell.services.Impl;

import neusoft.springbootsell.dataobject.ProductInfo;
import neusoft.springbootsell.enums.ProductStatusEnum;
import neusoft.springbootsell.enums.ResultEnum;
import neusoft.springbootsell.exception.SellException;
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
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        Page<ProductInfo> productInfoPage = repository.findAll(pageable);
        //TODO
        return productInfoPage;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void onSale(String productId) {
        //查一下
        ProductInfo pro = repository.findOne(productId);
        //进行判断
        if(pro==null){
           throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(pro.getProductStatus() == ProductStatusEnum.UP.getCode()){
            throw  new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更改值
        pro.setProductStatus(ProductStatusEnum.UP.getCode());
        repository.save(pro);
    }

    @Override
    public void offSale(String productId) {
        //查一下
        ProductInfo pro = repository.findOne(productId);
        //进行判断
        if(pro==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(pro.getProductStatus() == ProductStatusEnum.UP.getCode()){
            throw  new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        ///更改值
        pro.setProductStatus(ProductStatusEnum.DOWN.getCode());
        repository.save(pro);
    }

    @Override
    public void increaseStock(String productId) {

    }

    @Override
    public void decreaseStock(String productId) {

    }
}
