package neusoft.springbootsell.dataobject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetail,String> {
}
