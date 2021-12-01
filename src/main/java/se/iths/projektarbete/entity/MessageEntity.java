package se.iths.projektarbete.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private UserEntity user;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    @NonNull
    private FeedEntity feedEntity;

    @PrePersist
    public void getCurrentDate() {
        setCreatedAt(LocalDate.now());
    }
}
