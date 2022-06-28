package com.example.architecture.hexagonal.domain;

import com.example.architecture.hexagonal.domain.valueobjects.Description;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Announcement extends Content {
    Description description;
}

