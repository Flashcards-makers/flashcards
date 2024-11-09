package pl.ztp.flashcards.common;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@ComponentScan(basePackages = {"pl.ztp.flashcards.common.*"})
@EntityScan(basePackages = {"pl.ztp.flashcards.common.*"})
@EnableR2dbcRepositories
public class CommonConfig {

}
