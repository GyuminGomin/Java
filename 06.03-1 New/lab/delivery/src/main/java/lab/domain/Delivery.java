package lab.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lab.DeliveryApplication;
import lab.domain.DeliveryCancelled;
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

    private String address;

    private String customerId;

    private Integer quantity;

    private Long orderId;

    private String status;

    @PostPersist
    public void onPostPersist() {
        OrderListAdded orderListAdded = new OrderListAdded(this);
        orderListAdded.publishAfterCommit();

        DeliveryCancelled deliveryCancelled = new DeliveryCancelled(this);
        deliveryCancelled.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void addToDeliveryList(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        OrderListAdded orderListAdded = new OrderListAdded(delivery);
        orderListAdded.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            OrderListAdded orderListAdded = new OrderListAdded(delivery);
            orderListAdded.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void deliveryCancel(OrderCancelled orderCancelled) {
        //implement business logic here:

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryCancelled deliveryCancelled = new DeliveryCancelled(delivery);
        deliveryCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process*/
        
        repository().findById(orderCancelled.get()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryCancelled deliveryCancelled = new DeliveryCancelled(delivery);
            deliveryCancelled.publishAfterCommit();

         });
        

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
