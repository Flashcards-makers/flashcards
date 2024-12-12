package pl.ztp.flashcards.server.dto.request;

import java.util.List;

public record SaveFlashardsRequest(String name, String description, String icon, Boolean isPublic,
                                   List<PageDto> pages) {
}
