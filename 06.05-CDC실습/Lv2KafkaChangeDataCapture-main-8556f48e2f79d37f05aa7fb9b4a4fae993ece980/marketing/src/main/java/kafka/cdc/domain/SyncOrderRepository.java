package kafka.cdc.domain;

import kafka.cdc.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="syncOrders", path="syncOrders")
public interface SyncOrderRepository extends PagingAndSortingRepository<SyncOrder, Long>{

}
