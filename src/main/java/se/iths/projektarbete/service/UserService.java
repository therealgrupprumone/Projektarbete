package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.repo.UserRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public Iterable<UserEntity> findAll() {
        return userRepo.findAll();
    }

    public Optional<UserEntity> findById(Long id){
        return userRepo.findById(id);
    }
}
