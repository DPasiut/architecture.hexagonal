package com.example.architecture.hexagonal.domain;

import com.example.architecture.hexagonal.domain.exceptions.PublishContentException;
import com.example.architecture.hexagonal.domain.exceptions.UnpublishContentException;
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
    @NonNull
    @Getter
    DmsId dmsId;
    @NonNull
    Title title;
    @NonNull
    PageDate pageDate;
    @Getter
    PublishStatus publishStatus;
    PublishDate publishDate;
    @Getter
    Slug slug;

    public void unpublish() {
        if (!this.publishStatus.equals(PublishStatus.PUBLISHED)) {
            throw new UnpublishContentException(this.dmsId);
        }
        this.publishStatus = PublishStatus.UNPUBLISHED;
    }

    public void publish() {
        if (this.publishStatus.equals(PublishStatus.PUBLISHED)) {
            throw new PublishContentException(this.dmsId);
        }
        generateSlug();
        this.publishStatus = PublishStatus.PUBLISHED;
    }

    public void generateSlug() {
        if (slug == null) {
            this.slug = buildSlugWithRules();
        }
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
