package pl.ztp.flashcards.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ztp.flashcards.server.dto.response.FlashcardsListResponse;
import pl.ztp.flashcards.server.entity.FlashcardsEntity;

@Mapper(uses = {FlashcardsEntityToFlashcardsList.class})
public abstract class FlashcardsEntityToFlashcardsList implements pl.ztp.flashcards.common.mapper.Mapper<FlashcardsEntity, FlashcardsListResponse> {
    @Override
    public Class<FlashcardsEntity> getSource() {
        return FlashcardsEntity.class;
    }

    @Override
    public Class<FlashcardsListResponse> getDestination() {
        return FlashcardsListResponse.class;
    }

    public abstract FlashcardsListResponse sourceToDestination(FlashcardsEntity source);

    @Mapping(ignore = true, target = "pages")
    public abstract FlashcardsEntity destinationToSource(FlashcardsListResponse dest);

    @Override
    public FlashcardsListResponse sourceToDestination(Object source) {
        if (source instanceof FlashcardsEntity flashcardsEntity) {
            return sourceToDestination(flashcardsEntity);
        }
        return null;
    }

    @Override
    public FlashcardsEntity destinationToSource(Object dest) {
        if (dest instanceof FlashcardsListResponse flashcardsListResponse) {
            return destinationToSource(flashcardsListResponse);
        }
        return null;
    }
}
