package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

@AllArgsConstructor
public class Content {

    private static final String PATTERN = "yyyy-MMM-dd";
    static final String SPECIAL_CHARACTERS = "[^a-zA-Z\\d\\-]";
    @NonNull
    DmsId dmsId;
    @NonNull
    Title title;
    @NonNull
    PageDate pageDate;
    PublishStatus publishStatus;
    PublishDate publishDate;
    Slug slug;

    public Content(@NonNull DmsId dmsId, @NonNull Title title, @NonNull PageDate pageDate) {
        this.dmsId = dmsId;
        this.title = title;
        this.pageDate = pageDate;
        this.publishStatus = PublishStatus.DRAFT;
    }

    public void unpublish() {
    }

    public void publish() {
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

        String slug = this.title.value() + "-" +  formatDate(pageDate.value());
        return slug
                .replaceAll(whiteSpaces, "-")
                .replaceAll(multipleDashes, "-")
                .replaceAll(SPECIAL_CHARACTERS, "");
    }

    protected String formatDate(LocalDate value) {
        return value.format(DateTimeFormatter.ofPattern(PATTERN));
    }

}
