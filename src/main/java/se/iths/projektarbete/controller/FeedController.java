package se.iths.projektarbete.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.projektarbete.entity.FeedEntity;
import se.iths.projektarbete.service.FeedService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("feeds")
public class FeedController {

    private final FeedService feedService;

    @GetMapping
    public ResponseEntity<Iterable<FeedEntity>> getAllFeeds() {
        return ResponseEntity.ok(feedService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<FeedEntity>> getFeedById(@PathVariable Long id) {
        Optional<FeedEntity> foundFeed = feedService.findByFeedId(id);
        return new ResponseEntity<>(foundFeed, HttpStatus.OK);
    }
}
