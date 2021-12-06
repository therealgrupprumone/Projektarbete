package se.iths.projektarbete.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String username;
    @NonNull
    @JsonIgnore
    private String password;

    @ManyToMany(cascade = {
            CascadeType.ALL
    },
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<MessageEntity> messageEntitySet = new HashSet<>();

    public void addRole(RoleEntity roleEntity) {
        roles.add(roleEntity);
        roleEntity.getUserEntitySet().add(this);
    }

    public void addMessage(MessageEntity messageEntity) {
        messageEntitySet.add(messageEntity);
        messageEntity.setUser(this);
    }

    @JsonIgnore
    public Set<MessageEntity> getMessageEntitySet() {
        return messageEntitySet;
    }
}
