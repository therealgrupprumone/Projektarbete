package se.iths.projektarbete.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.projektarbete.entity.RoleEntity;

@Repository
public interface RoleRepo extends CrudRepository<RoleEntity, Long> {
    RoleEntity findByRole(String role);
}
