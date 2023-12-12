package kafka.cdc.domain;

import kafka.cdc.OrderApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="ORDER_TABLE")
@Data

public class Order  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    private Long id;
    
    private String customerId;
    
    private Integer qty;
    
    private Double price;

    private Long productId;


    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }






}
