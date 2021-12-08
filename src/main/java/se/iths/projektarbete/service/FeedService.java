package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.dto.Feed;
import se.iths.projektarbete.entity.FeedEntity;
import se.iths.projektarbete.mapper.FeedMapper;
import se.iths.projektarbete.repo.FeedRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedService {

    private final FeedMapper mapper;
    private FeedRepo feedRepo;

    public List<Feed> findAll() {


        List<se.iths.projektarbete.dto.Feed> allFeeds = new ArrayList<>();
        Iterable<FeedEntity> foundFeeds = feedRepo.findAll();

        foundFeeds.forEach(feed -> {
            allFeeds.add(mapper.toDto(feed));
        });
        return allFeeds;
    }

    public Feed findByFeedId(Long id) {
        return feedRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow();
    }

    public Feed createFeed(Feed feed) {
        return mapper.toDto(feedRepo.save(mapper.fromDto(feed)));
    }


}
