package com.example.architecture.hexagonal.infrastructure;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.exceptions.ContentNotFoundException;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Profile("dev")
class DatabaseContentRepository implements ContentRepository {

    private final MongoContentRepository contentRepository;
    private final ContentMapper contentMapper;

    @Override
    public Optional<Content> getById(DmsId id) {
        return contentRepository.findById(id.value()).map(contentMapper::mapToContent);
    }

    @Override
    public void save(Content content) {
        contentRepository.save(contentMapper.mapToMongoContent(content));
    }
}
