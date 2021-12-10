package se.iths.projektarbete.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.projektarbete.dto.Role;
import se.iths.projektarbete.dto.User;
import se.iths.projektarbete.exception.UserNameTakenException;
import se.iths.projektarbete.service.RoleService;
import se.iths.projektarbete.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAllRoles());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws UserNameTakenException {
        userService.createDtoUser(user);
        return ResponseEntity.ok(user);
    }
}
