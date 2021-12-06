package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.iths.projektarbete.dto.User;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.mapper.UserMapper;
import se.iths.projektarbete.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper mapper;

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        Iterable<UserEntity> foundUsers = userRepo.findAll();
        foundUsers.forEach(user -> {
            allUsers.add(mapper.toDto(user));
        });
        return allUsers;
    }

    public User getUser(Long id) {
        return userRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found for id " + id));
    }

    public User createUser(User user) {
        userRepo.save(mapper.fromDto(user));
        return user;
    }
}
