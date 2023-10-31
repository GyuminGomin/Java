package kafka.scaling.domain;

import java.util.*;
import kafka.scaling.domain.*;
import kafka.scaling.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class OrderModified extends AbstractEvent {

    private Long id;
    private String productId;
    private String productName;
    private Integer qty;
    private Long customerId;
    private String address;

    public OrderModified(Order aggregate) {
        super(aggregate);
    }

    public OrderModified() {
        super();
    }
}
