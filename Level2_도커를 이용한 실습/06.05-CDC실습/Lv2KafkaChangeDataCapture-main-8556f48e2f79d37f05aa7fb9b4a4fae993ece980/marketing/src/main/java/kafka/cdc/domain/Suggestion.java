package kafka.cdc.domain;

import kafka.cdc.MarketingApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Suggestion_table")
@Data

public class Suggestion  {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long productId;
    
    
    
    
    
    private Long suggestProductId1;
    
    
    
    
    
    private Long suggestProductId2;
    
    
    
    
    
    private Long suggestProductId3;

    @PostPersist
    public void onPostPersist(){
        // Get request from SyncOrder
        //kafka.cdc.external.SyncOrder syncOrder =
        //    Application.applicationContext.getBean(kafka.cdc.external.SyncOrderService.class)
        //    .getSyncOrder(/** mapping value needed */);

    }

    public static SuggestionRepository repository(){
        SuggestionRepository suggestionRepository = MarketingApplication.applicationContext.getBean(SuggestionRepository.class);
        return suggestionRepository;
    }






}
