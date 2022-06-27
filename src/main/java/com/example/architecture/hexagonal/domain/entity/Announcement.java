package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.valueobjects.Description;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import com.example.architecture.hexagonal.domain.valueobjects.PageDate;
import com.example.architecture.hexagonal.domain.valueobjects.Title;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class Announcement extends Content {
    @NonNull
    Description description;

    public Announcement(@NonNull DmsId dmsId, @NonNull Title title, @NonNull PageDate pageDate, @NonNull Description description) {
        super(dmsId, title, pageDate);
        this.description = description;
    }
}

