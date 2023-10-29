package labshopmonolith.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshopmonolith.MonolithApplication;
import labshopmonolith.domain.OrderPlaced;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productId;
    private Integer qty;
    private String customerId;
    private Double amount;

    @PostPersist
    public void onPostPersist() {
        labshopmonolith.external.DecreaseStockCommand decreaseStockCommand = new labshopmonolith.external.DecreaseStockCommand();
    
          // 주문수량 정보를 커맨드 객체에 적재한다. 
        decreaseStockCommand.setQty(getQty()); 
        
          // InventoryService Proxy를 통해 커맨드 객체와 함께 원격호출 한다.
        MonolithApplication.applicationContext
            .getBean(labshopmonolith.external.InventoryService.class)
            .decreaseStock((Long.valueOf(getProductId())), decreaseStockCommand);
    }

    @PrePersist
    public void onPrePersist() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = MonolithApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
