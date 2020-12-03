package neusoft.springbootsell.services;

import neusoft.springbootsell.dataobject.ProductInfo;
import neusoft.springbootsell.dataobject.SellerInfo;

public interface SellerService {
    //查询一个用户
    SellerInfo findOne(String id);
    //新增用户
    SellerInfo save(SellerInfo sellerInfo);
    //通过Id和password进行查询返回用户对象
    Boolean getByIdAndPassword(String Id,String password);
    //更新密码
    void updatePassword(String Id,String password);

}
