package se.iths.projektarbete.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Message {

    private Long id;
    @NonNull
    private String chatMessage;
    private String username;
    private LocalDateTime createdAt;

    private Long feedId;

}
