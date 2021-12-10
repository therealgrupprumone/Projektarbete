package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.dto.Role;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.mapper.RoleMapper;
import se.iths.projektarbete.repo.RoleRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleMapper mapper;
    private RoleRepo roleRepo;

    public List<Role> findAllRoles() {
        List<Role> allRoles = new ArrayList<>();
        Iterable<RoleEntity> foundRoles = roleRepo.findAll();
        foundRoles.forEach(role -> {
            allRoles.add(mapper.toDto(role));
        });
        return allRoles;
    }
}