package com.example.architecture.hexagonal.domain.entity;

import com.example.architecture.hexagonal.domain.entity.valueobjects.*;
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
}
