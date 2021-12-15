package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import se.iths.projektarbete.dto.Feed;
import se.iths.projektarbete.entity.FeedEntity;
import se.iths.projektarbete.mapper.FeedMapper;
import se.iths.projektarbete.repo.FeedRepo;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Component
@AllArgsConstructor
public class FeedService {

    private final FeedMapper mapper;
    private FeedRepo feedRepo;

    public Flux<List<Feed>> findAll() {
        List<Feed> allFeeds = new ArrayList<>();
        Iterable<FeedEntity> foundFeeds = feedRepo.findAll();
        foundFeeds.forEach(feed -> {
            allFeeds.add(mapper.toDto(feed));
        });
        Flux<Feed> fluxAllFeeds = (Flux<Feed>) allFeeds;
        return Flux
                .fromStream(Stream.generate(() -> allFeeds));
    }

    public Feed findByFeedId(Long id) {
        return feedRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Feed with id: " + id + " does not exist"));
    }

    public Feed createFeed(Feed feed) {
        return mapper.toDto(feedRepo.save(mapper.fromDto(feed)));
    }
}
