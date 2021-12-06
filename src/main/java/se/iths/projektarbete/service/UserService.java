package se.iths.projektarbete.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.dto.User;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.mapper.UserMapper;
import se.iths.projektarbete.repo.RoleRepo;
import se.iths.projektarbete.repo.UserRepo;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper mapper;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        Iterable<UserEntity> foundUsers = userRepo.findAll();
        System.out.println(foundUsers);
        foundUsers.forEach(user -> {
            allUsers.add(mapper.toDto(user));
        });
        return allUsers;
    }

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        RoleEntity roleToAdd = roleRepo.findByRole("ROLE_USER");
        userEntity.addRole(roleToAdd);
        return userRepo.save(userEntity);
    }

    public void deleteUser(Long id) {
        UserEntity foundUser = userRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userRepo.deleteById(foundUser.getId());
    }

    public User getUser(Long id) {
        return userRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("User with id: " + id + " does not exist"));
    }


    public User createUser(User user) {
        userRepo.save(mapper.fromDto(user));
        return user;
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepo.findById(id);

    }

}
