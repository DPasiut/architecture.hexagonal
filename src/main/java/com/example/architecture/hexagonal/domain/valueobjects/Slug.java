package com.example.architecture.hexagonal.domain.valueobjects;

public record Slug(String value) {
    private static final String WHITE_SPACES_REGEXP = "\\s";
    private static final String MULTIPLE_DASHES_REGEXP = "-+";
    private static final String SPECIAL_CHARACTERS_REGEXP = "[^a-zA-Z\\d\\-]";
    private static final String SPECIAL_CHARACTERS_IN_BEGIN_OF_STRING = "^[^a-zA-Z\\d]";

    public static Slug fromRawString(String value) {
        return new Slug(value.replaceAll(WHITE_SPACES_REGEXP, "-")
                .replaceAll(MULTIPLE_DASHES_REGEXP, "-")
                .replaceAll(SPECIAL_CHARACTERS_REGEXP, "")
                .replaceAll(SPECIAL_CHARACTERS_IN_BEGIN_OF_STRING, ""));
    }

}
