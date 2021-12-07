package se.iths.projektarbete.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import se.iths.projektarbete.entity.MessageEntity;
import se.iths.projektarbete.entity.RoleEntity;
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
    @JsonIgnore
    private String password;
    private Set<RoleEntity> roles = new HashSet<>();
    private Set<MessageEntity> messageEntitySet = new HashSet<>();

}
