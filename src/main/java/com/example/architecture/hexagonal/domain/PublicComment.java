package com.example.architecture.hexagonal.domain;

import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@SuperBuilder
public class PublicComment extends Content {
    private static final String EMPTY_STRING = "";
    @NonNull
    UpcomingDate upcomingDate;
    OpenForSubmissionDate openForSubmissionDate;
    CloseForSubmissionDate closeForSubmission;
    ReportDue reportDue;

    @Override
    protected Slug buildSlugWithRules() {
        Optional<LocalDate> latestDate = getLatestDate();
        StringBuilder slug = new StringBuilder();
        slug
                .append(this.title.value())
                .append('-')
                .append(formatDate(upcomingDate.value()));
        latestDate.ifPresent(localDate -> {
            if (!isEqualsToUpcomingDate(localDate)) {
                slug
                        .append('-')
                        .append(formatDate(latestDate.get()));
            }

        });

        return new Slug(slug.toString());
    }

    private Optional<LocalDate> getLatestDate() {
        if (reportDue != null) {
            return Optional.of(reportDue.value());
        }

        if (closeForSubmission != null) {
            return Optional.of(closeForSubmission.value());
        }

        if (openForSubmissionDate != null) {
            return Optional.of(openForSubmissionDate.value());
        }
        return Optional.empty();
    }

    private boolean isEqualsToUpcomingDate(LocalDate localDate) {
        return localDate.equals(upcomingDate.value());
    }
}
