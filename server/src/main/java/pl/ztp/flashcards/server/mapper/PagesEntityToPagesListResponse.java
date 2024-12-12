package pl.ztp.flashcards.server.mapper;

import pl.ztp.flashcards.server.dto.response.PagesListResponse;
import pl.ztp.flashcards.server.entity.PagesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {PagesEntityToPagesListResponse.class})
public abstract class PagesEntityToPagesListResponse implements pl.ztp.flashcards.common.mapper.Mapper<PagesEntity, PagesListResponse> {
    @Override
    public Class<PagesEntity> getSource() {
        return PagesEntity.class;
    }

    @Override
    public Class<PagesListResponse> getDestination() {
        return PagesListResponse.class;
    }

    public abstract PagesListResponse sourceToDestination(PagesEntity source);

    @Mapping(ignore = true, target = "flashcardId")
    public abstract PagesEntity destinationToSource(PagesListResponse dest);

    @Override
    public PagesListResponse sourceToDestination(Object source) {
        if (source instanceof PagesEntity flashcardsEntity) {
            return sourceToDestination(flashcardsEntity);
        }
        return null;
    }

    @Override
    public PagesEntity destinationToSource(Object dest) {
        if (dest instanceof PagesListResponse flashcardsListResponse) {
            return destinationToSource(flashcardsListResponse);
        }
        return null;
    }
}
