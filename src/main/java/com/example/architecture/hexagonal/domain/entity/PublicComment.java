package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.entity.types.*;
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

    public PublicComment(
            @NonNull DmsId dmsId,
            @NonNull Title title,
            @NonNull PageDate pageDate,
            @NonNull UpcomingDate upcomingDate
    ) {
        super(dmsId, title, pageDate);
        this.upcomingDate = upcomingDate;
    }

    @Override
    void generateSlug() {
    }
}
