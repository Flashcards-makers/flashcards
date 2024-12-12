package pl.ztp.flashcards.server.dto.request;

public record SaveAttemptRequest(Long pageId, String answer, Boolean isCorrect) {
}
