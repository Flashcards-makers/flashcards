package pl.ztp.flashcards.server.mapper;

import pl.ztp.flashcards.server.dto.request.PageDto;
import pl.ztp.flashcards.server.entity.PagesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {PageDtoToPagesEntityMapper.class})
public abstract class PageDtoToPagesEntityMapper implements pl.ztp.flashcards.common.mapper.Mapper<PageDto, PagesEntity> {

    @Override
    public Class<PageDto> getSource() {
        return PageDto.class;
    }

    @Override
    public Class<PagesEntity> getDestination() {
        return PagesEntity.class;
    }

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "flashcardId")
    @Mapping(ignore = true, target = "createdAt")
    public abstract PagesEntity sourceToDestination(PageDto source);


    public abstract PageDto destinationToSource(PagesEntity dest);

    @Override
    public PagesEntity sourceToDestination(Object source) {
        if (source instanceof PageDto saveFlashardsRequest) {
            return sourceToDestination(saveFlashardsRequest);
        }
        return null;
    }

    @Override
    public PageDto destinationToSource(Object dest) {
        if (dest instanceof PagesEntity pageDto) {
            return destinationToSource(pageDto);
        }
        return null;
    }
}
