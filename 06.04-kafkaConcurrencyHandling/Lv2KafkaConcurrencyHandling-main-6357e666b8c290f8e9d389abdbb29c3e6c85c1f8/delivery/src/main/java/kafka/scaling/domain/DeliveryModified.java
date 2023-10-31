package kafka.scaling.domain;

import java.util.*;
import kafka.scaling.domain.*;
import kafka.scaling.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class DeliveryModified extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long customerId;
    private String productId;
    private String productName;
    private Integer qty;
    private String address;
    private String status;

    public DeliveryModified(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryModified() {
        super();
    }
}
