package se.iths.projektarbete.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
    
    @JsonIgnore
    public Set<UserEntity> getUsers() {
        return users;
    }

}
