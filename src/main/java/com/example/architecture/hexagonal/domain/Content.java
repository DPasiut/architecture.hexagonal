package com.example.architecture.hexagonal.domain;

import com.example.architecture.hexagonal.domain.exceptions.ContentAlreadyPublishedException;
import com.example.architecture.hexagonal.domain.exceptions.ContentNotPublishedException;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@EqualsAndHashCode
@ToString
@SuperBuilder
@Getter
public class Content {

    protected static final String PATTERN = "yyyy-MMM-dd";
    @NonNull
    DmsId dmsId;
    @NonNull
    Title title;
    @NonNull
    PageDate pageDate;

    PublishStatus publishStatus;
    PublishDate publishDate;

    Slug slug;

    public void unpublish() {
        if (!this.publishStatus.equals(PublishStatus.PUBLISHED)) {
            throw new ContentNotPublishedException(this.dmsId);
        }
        this.publishStatus = PublishStatus.UNPUBLISHED;
    }

    public void publish() {
        if (this.publishStatus.equals(PublishStatus.PUBLISHED)) {
            throw new ContentAlreadyPublishedException(this.dmsId);
        }
        generateSlug();
        this.publishStatus = PublishStatus.PUBLISHED;
    }

    public void generateSlug() {
        if (slug == null) {
            this.slug = buildSlugWithRules();
        }
    }

    public boolean canDelete() {
        return this.publishStatus != PublishStatus.PUBLISHED;
    }

    protected Slug buildSlugWithRules() {
        StringBuilder slug = new StringBuilder();
        slug
                .append(this.title.value())
                .append('-')
                .append(formatDate(pageDate.value()));
        return new Slug(slug.toString());
    }

    protected static String formatDate(LocalDate value) {
        return value.format(DateTimeFormatter.ofPattern(PATTERN));
    }

}
