package kafka.cdc.domain;

import kafka.cdc.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="suggestions", path="suggestions")
public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, Long>{

}
