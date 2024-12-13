package pl.ztp.flashcards.server.mapper;

import org.mapstruct.Mapping;
import pl.ztp.flashcards.common.mapper.Mapper;
import pl.ztp.flashcards.server.dto.request.SaveFlashardsRequest;
import pl.ztp.flashcards.server.entity.FlashcardsEntity;

@org.mapstruct.Mapper(uses = {SaveFlashardsRequestToFlashcardsEntityMapper.class})
public abstract class SaveFlashardsRequestToFlashcardsEntityMapper implements Mapper<SaveFlashardsRequest, FlashcardsEntity> {

    @Override
    public Class<SaveFlashardsRequest> getSource() {
        return SaveFlashardsRequest.class;
    }

    @Override
    public Class<FlashcardsEntity> getDestination() {
        return FlashcardsEntity.class;
    }

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "createdAt")
    public abstract FlashcardsEntity sourceToDestination(SaveFlashardsRequest source);

    @Mapping(ignore = true, target = "pages")
    public abstract SaveFlashardsRequest destinationToSource(FlashcardsEntity dest);

    @Override
    public FlashcardsEntity sourceToDestination(Object source) {
        if (source instanceof SaveFlashardsRequest saveFlashardsRequest) {
            return this.sourceToDestination(saveFlashardsRequest);
        }
        return null;
    }

    @Override
    public SaveFlashardsRequest destinationToSource(Object dest) {
        if (dest instanceof FlashcardsEntity flashcardsEntity) {
            return destinationToSource(flashcardsEntity);
        }
        return null;
    }


}
