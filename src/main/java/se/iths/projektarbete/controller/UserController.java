package se.iths.projektarbete.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.service.RoleService;
import se.iths.projektarbete.service.UserService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("")
    public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("roles")
    public ResponseEntity<Iterable<RoleEntity>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<UserEntity>> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));

    }

}
