package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.entity.types.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Content {
    @NonNull
    DmsId dmsId;
    @NonNull
    Title title;
    @NonNull
    PageDate pageDate;
    PublishStatus publishStatus;
    PublishDate publishDate;
    Slug slug;

    public Content(DmsId dmsId, Title title, PageDate pageDate){
        this.dmsId = dmsId;
        this.title = title;
        this.pageDate = pageDate;
        this.publishStatus = PublishStatus.DRAFT;
    }

    void generateSlug(){
    }

    public void publish(){}
    public void unpublish(){}
}
