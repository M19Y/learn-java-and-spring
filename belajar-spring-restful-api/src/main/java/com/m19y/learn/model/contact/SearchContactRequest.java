package com.m19y.learn.model.contact;

import jakarta.validation.constraints.NotNull;

public record SearchContactRequest(
        String name,
        String email,
        String phone,
        @NotNull Integer page,
        @NotNull Integer size
) {
}
