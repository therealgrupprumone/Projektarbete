package se.iths.projektarbete.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Message {

    private Long id;
    @NonNull
    private String chatMessage;
    //    @NonNull
    private String username;
    private LocalDateTime createdAt;
    //    @NonNull
    private Long feedId;

}
