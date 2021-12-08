package se.iths.projektarbete.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Role {

    private Long id;
    private String role;
    private Set<User> users = new HashSet<>();

    public Role(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }


}
