package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.entity.types.Description;
import com.example.architecture.hexagonal.domain.entity.types.DmsId;
import com.example.architecture.hexagonal.domain.entity.types.PageDate;
import com.example.architecture.hexagonal.domain.entity.types.Title;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Announcement extends Content {
    @NonNull
    Description description;

    public Announcement(
            @NonNull DmsId dmsId,
            @NonNull Title title,
            @NonNull Description description,
            @NonNull PageDate pageDate
    ) {
        super(dmsId, title, pageDate);
        this.description = description;
    }
}

