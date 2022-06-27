package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

@Builder
@AllArgsConstructor
public class Content {
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
    }
    void generateSlug(){

    }
    public void unpublish(){}
}
