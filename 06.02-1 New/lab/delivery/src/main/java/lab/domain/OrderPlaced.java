package lab.domain;

import java.util.*;
import lab.domain.*;
import lab.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String prouductId;
    private Integer qty;
    private String customerId;
    private String amount;
    private String address;
    private String status;
}
