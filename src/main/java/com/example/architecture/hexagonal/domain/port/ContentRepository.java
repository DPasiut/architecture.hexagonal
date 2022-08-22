package com.example.architecture.hexagonal.domain.port;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

import java.util.List;
import java.util.Optional;

public interface ContentRepository {
    Optional<Content> getById(DmsId id);
    void save(Content content);
    void delete(DmsId id);
}
