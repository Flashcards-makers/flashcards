package pl.ztp.flashcards.server


import pl.ztp.flashcards.common.dto.UserInfoUserDetails
import pl.ztp.flashcards.server.dto.request.PageDto
import pl.ztp.flashcards.server.dto.request.SaveFlashardsRequest
import pl.ztp.flashcards.server.dto.response.FlashcardsListResponse
import pl.ztp.flashcards.server.dto.response.PagesListResponse
import pl.ztp.flashcards.server.service.FlashcardsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.test.context.TestSecurityContextHolder
import org.springframework.security.test.context.support.ReactorContextTestExecutionListener
import org.springframework.test.context.TestExecutionListener
import org.springframework.test.web.reactive.server.WebTestClient

class PagesSpecification extends BaseSpecification {

    @Autowired
    private WebTestClient webTestClient

    @Autowired
    private FlashcardsService flashcardsService;

    private TestExecutionListener reactorContextTestExecutionListener = new ReactorContextTestExecutionListener();

    private void saveFlashcards(){
        UserInfoUserDetails userDetails = new UserInfoUserDetails(user)
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER")
        TestSecurityContextHolder.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), List.of(simpleGrantedAuthority)))
        reactorContextTestExecutionListener.beforeTestMethod(null)

        PageDto pageDto = new PageDto("question", "questionImage", "response", "responseImage")
        flashcardsService.saveFlashcards(new SaveFlashardsRequest("name", "desc", "icon", Boolean.TRUE, List.of(pageDto, pageDto)), userDetails).block()
    }

    def "GET /pages/{flashcardId} endpoint should return 2xx status"() {
        given:
        saveFlashcards()

        expect:
        FlashcardsListResponse response = flashcardsService.getFlashcards(null, new UserInfoUserDetails(user)).blockFirst()
        and: "status 2xx"
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pages/{flashcardId}")
                        .build(response.getId()))
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(PagesListResponse.class)
    }
}
