package se.iths.projektarbete.dto;

import lombok.*;
import se.iths.projektarbete.entity.FeedEntity;
import se.iths.projektarbete.entity.UserEntity;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Message {

    private Long id;
    private String message;
    private UserEntity user;
    private LocalDateTime createdAt;
    private FeedEntity feedEntity;

}
