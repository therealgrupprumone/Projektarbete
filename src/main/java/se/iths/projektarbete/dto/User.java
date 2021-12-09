package se.iths.projektarbete.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Set<Role> roles = new HashSet<>();
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
        message.setUsername(this.username);
    }

    @JsonIgnore
    public Set<Message> getMessages() {
        return messages;
    }
}
