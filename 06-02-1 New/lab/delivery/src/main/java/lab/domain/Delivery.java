package lab.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lab.DeliveryApplication;
import lab.domain.OrderListAdded;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    private String customerId;

    private String address;

    @PostPersist
    public void onPostPersist() {
        // OrderListAdded orderListAdded = new OrderListAdded(this);
        // orderListAdded.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void addOrderList(OrderPlaced orderPlaced) {
        //implement business logic here:

        /*Example 1:  new item*/ 
        Delivery delivery = new Delivery();
        delivery.setAddress(orderPlaced.getAddress());
        delivery.setCustomerId(orderPlaced.getCustomerId());
        delivery.setOrderId(String.valueOf(orderPlaced.getId()));
        repository().save(delivery);

        OrderListAdded orderListAdded = new OrderListAdded(delivery);
        orderListAdded.publishAfterCommit();
        

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
