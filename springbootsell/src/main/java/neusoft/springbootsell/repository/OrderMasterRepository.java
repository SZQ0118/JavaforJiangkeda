package neusoft.springbootsell.repository;

import neusoft.springbootsell.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{
}
