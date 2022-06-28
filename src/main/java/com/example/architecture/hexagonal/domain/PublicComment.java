package com.example.architecture.hexagonal.domain;

import com.example.architecture.hexagonal.domain.valueobjects.CloseForSubmissionDate;
import com.example.architecture.hexagonal.domain.valueobjects.OpenForSubmissionDate;
import com.example.architecture.hexagonal.domain.valueobjects.Slug;
import com.example.architecture.hexagonal.domain.valueobjects.UpcomingDate;
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
        String latestDate = formatDate(getLatestDate());

        String slug = this.title.value() + "-" + formatDate(this.pageDate.value()) + (latestDate.equals("") ? "" : "-" + latestDate );
        return slug
                .replaceAll(whiteSpaces, "-")
                .replaceAll(multipleDashes, "-")
                .replaceAll(SPECIAL_CHARACTERS, "");
    }

    private Optional<LocalDate> getLatestDate() {
        if (closeForSubmission != null) {
            return Optional.ofNullable(closeForSubmission.value());
        }
        if (openForSubmissionDate != null) {
            return Optional.ofNullable(openForSubmissionDate.value());
        }
        if (publishDate != null) {
            return Optional.ofNullable(publishDate.value());
        }
        return Optional.empty();
    }

    protected String formatDate(Optional<LocalDate> value) {
        return value.map(date -> date.format(DateTimeFormatter.ofPattern(PATTERN))).orElse(EMPTY_STRING);
    }
}
