package se.iths.projektarbete.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "feed")
public class FeedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @OneToMany(mappedBy = "feedEntity", cascade = CascadeType.ALL)
    private List<MessageEntity> messageEntityList;

}
