package com.jhyuk.tutorialdocs;

import jakarta.annotation.Nonnull;

public record Member(
        Long id,
        @Nonnull
        String name,
        String phone
) {
}
