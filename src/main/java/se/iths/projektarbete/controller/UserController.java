package se.iths.projektarbete.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<Iterable<UserEntity>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

}
