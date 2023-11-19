package labshopcqrs.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshopcqrs.DeliveryApplication;
import labshopcqrs.domain.DeliveryStarted;
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

    @PostPersist
    public void onPostPersist() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public static void addToDeliveryList(OrderPlaced orderPlaced) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderPlaced.getId());
        delivery.setAddress(orderPlaced.getAddress());
        delivery.setQuantity(orderPlaced.getQty());
        delivery.setCustomerId(orderPlaced.getCustomerId());

        repository().save(delivery);
    }
}
//>>> DDD / Aggregate Root
