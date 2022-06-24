package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.valueobjects.Description;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Announcement extends Content {
    @NonNull
    Description description;
}

