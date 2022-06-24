package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.entity.valueobjects.*;
import com.example.architecture.hexagonal.domain.valueobjects.CloseForSubmissionDate;
import com.example.architecture.hexagonal.domain.valueobjects.OpenForSubmissionDate;
import com.example.architecture.hexagonal.domain.valueobjects.PublishDate;
import com.example.architecture.hexagonal.domain.valueobjects.UpcomingDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
class PublicComment extends Content {

    @NonNull
    UpcomingDate upcomingDate;
    OpenForSubmissionDate openForSubmissionDate;
    CloseForSubmissionDate closeForSubmission;
    PublishStatus publishStatus;
    PublishDate publishDate;

    @Override
    void generateSlug() {
    }
}
