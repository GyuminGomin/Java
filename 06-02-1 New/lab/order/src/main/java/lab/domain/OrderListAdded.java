package lab.domain;

import java.util.*;
import lab.domain.*;
import lab.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class OrderListAdded extends AbstractEvent {

    private Long id;
    private String orderId;
    private String customerId;
    private String address;
}
