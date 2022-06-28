package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PublicComment extends Content {

    @NonNull
    UpcomingDate upcomingDate;
    OpenForSubmissionDate openForSubmissionDate;
    CloseForSubmissionDate closeForSubmission;
    PublishDate publishDate;
    PublishStatus publishStatus;


    public PublicComment(@NonNull DmsId dmsId, @NonNull Title title, @NonNull PageDate pageDate, @NonNull UpcomingDate upcomingDate) {
        super(dmsId, title, pageDate);
        this.upcomingDate = upcomingDate;
    }

    @Override
    public void generateSlug() {
        if (slug == null) {
            String slug = buildSlugWithRules();
            this.slug = new Slug(slug);
        }
    }

    private String buildSlugWithRules() {
        String whiteSpaces = "\\s";
        String multipleDashes = "-+";
        String latestDate = chooseDate();
        String slug = this.title.value() + "-" + formatDate(this.pageDate.value()) + (!latestDate.equals("") ? "-" + latestDate : "");
        return slug
                .replaceAll(whiteSpaces, "-")
                .replaceAll(multipleDashes,"-")
                .replaceAll(SPECIAL_CHARACTERS,"");
    }

    private String chooseDate(){
        List<LocalDate> localDates = new ArrayList<>();
        localDates.add(upcomingDate != null ? upcomingDate.value() : null);
        localDates.add(openForSubmissionDate != null ? openForSubmissionDate.value() : null);
        localDates.add(closeForSubmission != null ? closeForSubmission.value() : null);
        while (localDates.remove(null));

        LocalDate latestDate = Collections.max(localDates);

        return latestDate != null ? formatDate(latestDate) : "";
    }
}
