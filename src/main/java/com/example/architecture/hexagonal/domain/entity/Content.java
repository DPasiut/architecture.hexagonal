package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.entity.types.PublishStatus;
import com.example.architecture.hexagonal.domain.entity.valueobjects.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
@Builder
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

    void generateSlug(){
    }

    public void publish(){}
    public void unpublish(){}
}
