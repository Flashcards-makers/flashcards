package pl.ztp.flashcards.server;

import jakarta.annotation.PostConstruct;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.ztp.flashcards.common.mapper.MapFactory;
import pl.ztp.flashcards.server.mapper.*;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @PostConstruct
    public void registerMappers() {
        MapFactory.registerMappers(
                Mappers.getMapper(PageDtoToPagesEntityMapper.class),
                Mappers.getMapper(SaveFlashardsRequestToFlashcardsEntityMapper.class),
                Mappers.getMapper(FlashcardsEntityToFlashcardsList.class),
                Mappers.getMapper(PagesEntityToPagesListResponse.class),
                Mappers.getMapper(SaveAttemptRequestToResolvedEntity.class)
        );
    }
}
