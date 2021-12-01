package se.iths.projektarbete.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.projektarbete.entity.FeedEntity;

@Repository
public interface FeedRepo extends CrudRepository<FeedEntity, Long> {
}
