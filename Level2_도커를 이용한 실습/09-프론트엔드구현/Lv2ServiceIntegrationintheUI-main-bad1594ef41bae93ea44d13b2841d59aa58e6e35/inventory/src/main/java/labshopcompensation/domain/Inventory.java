package labshopcompensation.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshopcompensation.InventoryApplication;
import lombok.Data;

@Entity
@Table(name = "Inventory_table")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long stock;

    @PostPersist
    public void onPostPersist() {}

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = InventoryApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    public static void decreaseStock(OrderPlaced orderPlaced) {

        repository().findById(Long.valueOf(orderPlaced.getProductId())).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() - orderPlaced.getQty()); 
            repository().save(inventory);


         });
      

        
    }

    public static void increaseStock(OrderCancelled orderCancelled) {

        repository().findById(Long.valueOf(orderCancelled.getProductId())).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() + orderCancelled.getQty()); 
            repository().save(inventory);


         });

    }


}
