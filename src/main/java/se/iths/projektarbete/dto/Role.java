package se.iths.projektarbete.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import se.iths.projektarbete.entity.UserEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private Set<UserEntity> userEntitySet = new HashSet<>();
    public Set<UserEntity> getUserEntitySet() {
        return userEntitySet;
    }

}
