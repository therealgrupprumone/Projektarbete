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
    private String chatMessage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NonNull
    private UserEntity user;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    @NonNull
    private FeedEntity feed;

    public UserEntity getUser() {
        return user;
    }

    @PrePersist
    public void getCurrentDate() {
        setCreatedAt(LocalDateTime.now());
    }

    @JsonIgnore
    public FeedEntity getFeed() {
        return feed;
    }
}
