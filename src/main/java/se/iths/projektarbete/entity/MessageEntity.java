package se.iths.projektarbete.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "message")
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

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    @NonNull
    private FeedEntity feedEntity;

    @PrePersist
    public void getCurrentDate() {
        setCreatedAt(LocalDateTime.now());
    }

    @JsonIgnore
    public FeedEntity getFeedEntity() {
        return feedEntity;
    }
}
