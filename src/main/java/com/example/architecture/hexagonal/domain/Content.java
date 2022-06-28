package com.example.architecture.hexagonal.domain;

import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import jdk.jshell.spi.ExecutionControl;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuperBuilder
public class Content {
    protected static final String PATTERN = "yyyy-MMM-dd";
    static final String SPECIAL_CHARACTERS = "[^a-zA-Z\\d\\-]";
    @NonNull
    DmsId dmsId;
    @NonNull
    Title title;
    @NonNull
    PageDate pageDate;
    PublishStatus publishStatus;
    PublishDate publishDate;
    @Getter
    Slug slug;
    public void unpublish() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented method");
    }

    public void publish() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented method");
    }

    public void generateSlug() {
        if (slug == null) {
            String slug = buildSlugWithRules();
            this.slug = new Slug(slug);
        }
    }

    private String buildSlugWithRules() {
        String whiteSpaces = "\\s";
        String multipleDashes = "-+";

        String slug = this.title.value() + "-" + formatDate(pageDate.value());
        return slug
                .replaceAll(whiteSpaces, "-")
                .replaceAll(multipleDashes, "-")
                .replaceAll(SPECIAL_CHARACTERS, "");
    }

    protected String formatDate(LocalDate value) {
        return value.format(DateTimeFormatter.ofPattern(PATTERN));
    }

}
