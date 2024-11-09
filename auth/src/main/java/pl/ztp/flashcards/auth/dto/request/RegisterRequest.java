package pl.ztp.flashcards.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String userName,
        @NotBlank
        String password,
        @Email
        String email
) { }
