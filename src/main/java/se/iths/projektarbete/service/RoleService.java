package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.repo.RoleRepo;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepo roleRepo;

    public Iterable<RoleEntity> findAll() {
        return roleRepo.findAll();
    }

}
