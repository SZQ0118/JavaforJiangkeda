package neusoft.springbootsell.dataobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class OderController {
    @Autowired
    private OrderRepository orderRepository;
}