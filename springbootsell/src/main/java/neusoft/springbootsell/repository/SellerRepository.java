package neusoft.springbootsell.repository;


import neusoft.springbootsell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerInfo,String> {
}
