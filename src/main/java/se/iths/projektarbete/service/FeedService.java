package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.entity.FeedEntity;
import se.iths.projektarbete.repo.FeedRepo;

@Service
@AllArgsConstructor
public class FeedService {

    private FeedRepo feedRepo;

    public Iterable<FeedEntity> findAll() {
        return feedRepo.findAll();
    }
}
