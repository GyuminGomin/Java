package kafka.scaling.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import kafka.scaling.DeliveryApplication;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Long customerId;

    private String productId;

    private String productName;

    private Integer qty;

    private String address;

    private String status;

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public static void startDelivery(OrderPlaced orderPlaced) {
        // Example 1:  new item 
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderPlaced.getId());
        delivery.setProductId(orderPlaced.getProductId());
        delivery.setProductName(orderPlaced.getProductName());
        delivery.setCustomerId(orderPlaced.getCustomerId());
        delivery.setQty(orderPlaced.getQty());
        delivery.setAddress(orderPlaced.getAddress());
        delivery.setStatus("DELIVERY STARTED");

        repository().save(delivery);

        DeliveryStarted deliveryStarted = new DeliveryStarted(delivery);
        deliveryStarted.publishAfterCommit();

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryStarted deliveryStarted = new DeliveryStarted(delivery);
            deliveryStarted.publishAfterCommit();

         });
        */

    }

    public static void modifyDelivery(OrderModified orderModified) {
        if (orderModified.getCustomerId()==2000L)   // KIM이 수정할 경우, 강제 Delay
            try {
                Thread.sleep(20000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        repository().findByOrderId(orderModified.getId()).ifPresentOrElse(delivery ->{  
            
            delivery.setAddress(orderModified.getAddress()); // do something
            delivery.setStatus("DELIVERY MODIFIED");
            repository().save(delivery);

            DeliveryModified deliveryModified = new DeliveryModified(delivery);
            deliveryModified.publishAfterCommit();

         }
         ,()->{
             throw new RuntimeException("\n\n#####  수정 대상을 찾을 수 없습니다.\n\n");
         }
        );
    }
    public static void cancelDelivery(OrderCancelled orderCancelled) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryCancelled deliveryCancelled = new DeliveryCancelled(delivery);
        deliveryCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process */
        
        repository().findByOrderId(orderCancelled.getId()).ifPresentOrElse(delivery ->{  
            
            // do something
            delivery.setStatus("DELIVERY CANCELLED");
            repository().save(delivery);

            DeliveryCancelled deliveryCancelled = new DeliveryCancelled(delivery);
            deliveryCancelled.publishAfterCommit();

        }
        ,()->{
            throw new RuntimeException("\n\n#####  삭제 대상을 찾을 수 없습니다.\n\n");
        }
       );
   }
}
