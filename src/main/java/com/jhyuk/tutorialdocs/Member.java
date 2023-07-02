package com.jhyuk.tutorialdocs;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Schema(description = "맴버 DTO")
public record Member(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY,description = "맴버 ID", nullable = true)
        @Null
        Long id,

        @Schema(description = "맴버 이름", nullable = false, example = "홍길동")
        @NotNull
        @Nonnull
        String name,

        @Schema(description = "맴버 전화 번호", nullable = true, example = "010-1234-1234")
        String phone
) {
}
