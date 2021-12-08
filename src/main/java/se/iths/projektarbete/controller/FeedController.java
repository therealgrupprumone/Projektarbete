package se.iths.projektarbete.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.projektarbete.dto.Feed;
import se.iths.projektarbete.service.FeedService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("feeds")
public class FeedController {

    private final FeedService feedService;

    @GetMapping
    public ResponseEntity<List<Feed>> getAllFeeds() {
        return ResponseEntity.ok(feedService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Feed> getFeedById(@PathVariable Long id) {
        return ResponseEntity.ok(feedService.findByFeedId(id));
    }
}
