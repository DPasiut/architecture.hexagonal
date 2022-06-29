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
        String latestDate = formatDate(getLatestDate());
        StringBuilder slug = new StringBuilder();
        slug
                .append(this.title.value())
                .append('-')
                .append(formatDate(upcomingDate.value()));
        if (!latestDate.equals("")) {
            slug.append('-').append(latestDate);
        }
        return Slug.fromRawString(slug.toString());
    }

    private Optional<LocalDate> getLatestDate() {
        if (reportDue != null) {
            LocalDate localDate = reportDue.value();
            return localDate != upcomingDate.value() ? Optional.ofNullable(localDate) : Optional.empty();
        }
        if (closeForSubmission != null) {
            LocalDate localDate = closeForSubmission.value();
            return localDate != upcomingDate.value() ? Optional.ofNullable(localDate) : Optional.empty();
        }
        if (openForSubmissionDate != null) {
            LocalDate localDate = openForSubmissionDate.value();
            return localDate != upcomingDate.value() ? Optional.ofNullable(localDate) : Optional.empty();
        }
        return Optional.empty();
    }

    protected String formatDate(Optional<LocalDate> value) {
        return value.map(date -> date.format(DateTimeFormatter.ofPattern(PATTERN))).orElse(EMPTY_STRING);
    }
}
