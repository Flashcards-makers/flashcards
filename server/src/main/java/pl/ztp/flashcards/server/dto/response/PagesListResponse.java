package pl.ztp.flashcards.server.dto.response;

import java.time.LocalDateTime;

public record PagesListResponse(Long id, String question, String questionImage, String answer, String answerImage,
                                LocalDateTime createdAt) {
}
