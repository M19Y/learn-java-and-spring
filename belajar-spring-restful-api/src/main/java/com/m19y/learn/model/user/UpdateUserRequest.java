package com.m19y.learn.model.user;

import jakarta.validation.constraints.Size;

public record UpdateUserRequest(@Size(max = 100) String name,
                                @Size(max = 100) String password) {
}
