package pl.ztp.flashcards.mail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ztp.flashcards.mail.repository.ActivateRepository;

@Service
@RequiredArgsConstructor
public class ActivateService {
    private final ActivateRepository activateRepository;

    public void markAsSend(Long id, String uuid) {
        activateRepository.findByUserId(id)
                .ifPresent(entity -> {
                    entity.markAsSent(uuid);
                    activateRepository.save(entity);
                });
    }
}