package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
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

    @Override
    void generateSlug() {
        if (slug == null) {
            String slug = buildSlugWithRules();
            this.slug = new Slug(slug);
        }
    }

    private String buildSlugWithRules() {
        String specialCharacters = "[^a-zA-Z\\d\\-]";
        String whiteSpaces = "\\s";
        String multipleDashes = "-+";

        String slug = this.title.value() + "-" + this.pageDate.value().toString();
        return slug
                .replaceAll(whiteSpaces, "-")
                .replaceAll(multipleDashes,"-")
                .replaceAll(specialCharacters,"");
    }
}
