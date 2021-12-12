package se.iths.projektarbete.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles = new HashSet<>();
    private Set<Message> messages = new HashSet<>();

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    @JsonIgnore
    public Set<Message> getMessages() {
        return messages;
    }

    public String getUserRoles() {
        return roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.joining(", "));
    }

    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }
}