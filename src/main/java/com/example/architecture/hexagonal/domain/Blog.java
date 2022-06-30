package com.example.architecture.hexagonal.domain;

import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Blog extends Content {

    @NonNull
    Text textOne;
    @NonNull
    Image imageOne;

    Text textTwo;
    Image imageTwo;
}
