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
        Optional<MongoContent> mongoContent = contentRepository.findById(id.value());
        if (mongoContent.isPresent()){
            return contentMapper.mapToContent(mongoContent.get());
        }
        return Optional.empty();
    }

    @Override
    public void save(Content content) {
        contentRepository.save(contentMapper.mapToMongoContent(content));
    }
}
