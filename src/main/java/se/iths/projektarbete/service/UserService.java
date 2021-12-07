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
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper mapper;
    private final RoleRepo roleRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepo userRepo, UserMapper mapper, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.mapper = mapper;
        this.roleRepo = roleRepo;
    }

    public List<User> getAllUsers() {
        Iterable<UserEntity> foundUsers = userRepo.findAll();

        return StreamSupport.stream(foundUsers.spliterator(), false)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // TODO Justera denna till att använda DTO?
    @Transactional
    public UserEntity createUserEntity(UserEntity userEntity, String role) {
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

    public User getUser(Long id) {
        return userRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("User with id: " + id + " does not exist"));
    }

    public User createDtoUser(User user) {
        userRepo.save(mapper.fromDto(user));
        return user;
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepo.findById(id);

    }

}
