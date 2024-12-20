package pl.ztp.flashcards.auth.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(String name, String surname, String userName, String jwtToken, String refreshToken) {
}
