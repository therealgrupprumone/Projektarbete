package se.iths.projektarbete.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import se.iths.projektarbete.dto.Feed;
import se.iths.projektarbete.entity.FeedEntity;

@Mapper(componentModel = "spring")
public interface FeedMapper {

    @Autowired
    FeedMapper INSTANCE = Mappers.getMapper(FeedMapper.class);

    Feed toDto(FeedEntity entity);

    FeedEntity fromDto(Feed feed);

}
