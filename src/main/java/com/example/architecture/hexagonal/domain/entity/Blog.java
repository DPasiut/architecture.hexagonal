package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.entity.types.*;
import lombok.Builder;
import lombok.NonNull;

@Builder
class Blog extends Content {

    @NonNull
    Text textOne;
    @NonNull
    Image imageOne;

    Text textTwo;
    Image imageTwo;


    public Blog(
            @NonNull DmsId dmsId,
            @NonNull Title title,
            @NonNull PageDate pageDate,
            @NonNull Text textOne,
            @NonNull Image imageOne
    ) {
        super(dmsId, title, pageDate);
        this.textOne = textOne;
        this.imageOne = imageOne;
    }

}
