package neusoft.springbootsell.services.Impl;

import neusoft.springbootsell.dataobject.SellerInfo;
import neusoft.springbootsell.repository.SellerRepository;
import neusoft.springbootsell.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerInfo findOne(String id) {
        return sellerRepository.findOne(id);
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo) {
        return sellerRepository.save(sellerInfo);
    }

    @Override
    public Boolean getByIdAndPassword(String Id, String password) {
        SellerInfo info = sellerRepository.findOne(Id);
        if(info==null){
            return false;
        }
        if(info.getPassword().equals(password)){
            return  true;
        }else
            return false;
    }

    @Override
    public void updatePassword(String Id, String password) {

    }
}
