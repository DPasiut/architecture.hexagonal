package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.Builder;
import lombok.NonNull;


public class PublicComment extends Content {

    @NonNull
    UpcomingDate upcomingDate;
    OpenForSubmissionDate openForSubmissionDate;
    CloseForSubmissionDate closeForSubmission;
    PublishStatus publishStatus;
    PublishDate publishDate;

    public PublicComment(@NonNull DmsId dmsId, @NonNull Title title, @NonNull PageDate pageDate, @NonNull UpcomingDate upcomingDate) {
        super(dmsId, title, pageDate);
        this.upcomingDate = upcomingDate;
    }

}
