package pl.ztp.flashcards.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserNameRequest(
        @NotBlank
        String userName
) { }
