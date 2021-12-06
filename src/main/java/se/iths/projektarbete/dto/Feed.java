package se.iths.projektarbete.dto;

import lombok.*;
import se.iths.projektarbete.entity.MessageEntity;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Feed {

    private Long id;
    private List<MessageEntity> messageEntityList;

}
