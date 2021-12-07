package se.iths.projektarbete.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.repo.RoleRepo;
import se.iths.projektarbete.repo.UserRepo;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Transactional
    public UserEntity createUser(UserEntity userEntity, String role) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        RoleEntity roleToAdd = roleRepo.findByRole(role);
        userEntity.addRole(roleToAdd);
        return userRepo.save(userEntity);
    }

    public void deleteUser(Long id) {
        UserEntity foundUser = userRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userRepo.deleteById(foundUser.getId());
    }

    public Iterable<UserEntity> findAll() {
        return userRepo.findAll();
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepo.findById(id);
    }

}
