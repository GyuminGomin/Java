package labshopcqrs.infra;

import java.util.List;
import labshopcqrs.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "mypages", path = "mypages")
public interface MypageRepository
    extends PagingAndSortingRepository<Mypage, Long> {
    List<Mypage> findByOrderId(Long orderId);
}
