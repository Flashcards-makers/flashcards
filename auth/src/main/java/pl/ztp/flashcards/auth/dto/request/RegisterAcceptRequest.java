package pl.ztp.flashcards.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterAcceptRequest(@NotBlank String uid) {
}
