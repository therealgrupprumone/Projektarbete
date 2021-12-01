package se.iths.projektarbete.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.projektarbete.entity.MessageEntity;

@Repository
public interface MessageRepo extends CrudRepository<MessageEntity, Long> {
}
