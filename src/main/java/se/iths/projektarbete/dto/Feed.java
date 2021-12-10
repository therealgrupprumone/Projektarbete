package se.iths.projektarbete.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Feed {

    private Long id;
    private List<Message> chatMessages;

}
