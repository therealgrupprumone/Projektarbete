package se.iths.projektarbete.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.dto.Role;
import se.iths.projektarbete.dto.User;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.exception.UserNameTakenException;
import se.iths.projektarbete.mapper.UserMapper;
import se.iths.projektarbete.repo.UserRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepo userRepo, UserMapper mapper) {
        this.userRepo = userRepo;
        this.userMapper = mapper;
    }

    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepo.findAll().spliterator(), false)
                .map(userMapper::toDto)
                .toList();
    }

    public User createUser(User user) throws UserNameTakenException {

        System.out.println("createUser in UserService called: " + user.toString());
        if (isUsernameTaken(user.getUsername()))
            throw new UserNameTakenException("Please change username, " + user.getUsername() + " is taken");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = new Role("ROLE_USER");
        user.addRole(role);

        UserEntity dtoToUserEntity = userMapper.fromDto(user);

        return userMapper.toDto(userRepo.save(dtoToUserEntity));

    }

    public User createAdmin(User user) throws UserNameTakenException {
        if (isUsernameTaken(user.getUsername()))
            throw new UserNameTakenException("Please change username, " + user.getUsername() + " is taken");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = new Role("ROLE_ADMIN");
        user.addRole(role);

        UserEntity dtoToUserEntity = userMapper.fromDto(user);

        return userMapper.toDto(userRepo.save(dtoToUserEntity));

    }


    public void deleteUser(Long id) {
        UserEntity foundUser = userRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        userRepo.delete(foundUser);
    }

    public User getUser(Long id) {
        return userRepo.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("User with id: " + id + " does not exist"));
    }

    private boolean isUsernameTaken(String username) {
        return findByUsername(username) != null;
    }

    private UserEntity findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
