package se.iths.projektarbete.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {

    private Long id;
    private String username;
    private String password;
    @NonNull
    private Set<Role> roles = new HashSet<>();
    @NonNull
    private Set<Message> messages = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);

    }

    public void addMessage(Message message) {
        messages.add(message);
        message.setUser(this);
    }

}
