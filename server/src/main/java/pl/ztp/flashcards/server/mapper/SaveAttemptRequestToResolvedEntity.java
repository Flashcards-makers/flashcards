package pl.ztp.flashcards.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.ztp.flashcards.server.dto.request.SaveAttemptRequest;
import pl.ztp.flashcards.server.entity.ResolvedEntity;

@Mapper(uses = {SaveAttemptRequestToResolvedEntity.class})
public abstract class SaveAttemptRequestToResolvedEntity implements pl.ztp.flashcards.common.mapper.Mapper<SaveAttemptRequest, ResolvedEntity> {
    @Override
    public Class<SaveAttemptRequest> getSource() {
        return SaveAttemptRequest.class;
    }

    @Override
    public Class<ResolvedEntity> getDestination() {
        return ResolvedEntity.class;
    }

    @Mapping(ignore = true, target = "userId")
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    public abstract ResolvedEntity sourceToDestination(SaveAttemptRequest source);

    public abstract SaveAttemptRequest destinationToSource(ResolvedEntity dest);

    @Override
    public ResolvedEntity sourceToDestination(Object source) {
        if (source instanceof SaveAttemptRequest flashcardsEntity) {
            return sourceToDestination(flashcardsEntity);
        }
        return null;
    }

    @Override
    public SaveAttemptRequest destinationToSource(Object dest) {
        if (dest instanceof ResolvedEntity flashcardsListResponse) {
            return destinationToSource(flashcardsListResponse);
        }
        return null;
    }
}
