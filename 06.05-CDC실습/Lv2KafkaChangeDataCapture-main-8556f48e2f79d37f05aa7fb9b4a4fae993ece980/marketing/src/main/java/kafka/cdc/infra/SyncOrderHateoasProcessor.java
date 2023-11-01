package kafka.cdc.infra;
import kafka.cdc.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class SyncOrderHateoasProcessor implements RepresentationModelProcessor<EntityModel<SyncOrder>>  {

    @Override
    public EntityModel<SyncOrder> process(EntityModel<SyncOrder> model) {

        
        return model;
    }
    
}
