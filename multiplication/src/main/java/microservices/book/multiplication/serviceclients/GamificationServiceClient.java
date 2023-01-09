package microservices.book.multiplication.serviceclients;

import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.challenge.ChallengeAttempt;
import microservices.book.multiplication.challenge.ChallengeSolvedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GamificationServiceClient {
    private final RestTemplate restTemplate;
    private final String gamificationHostUrl;

    public GamificationServiceClient(final RestTemplateBuilder builder,
                                     @Value("${service.gamification.host}") final String gamificationHostUrl) {
        restTemplate = builder.build();
        this.gamificationHostUrl = gamificationHostUrl;
    }

    public boolean sendAttempt(final ChallengeAttempt attempt) {
        try {
            ChallengeSolvedEvent dto = new ChallengeSolvedEvent(attempt.getId(),
                    attempt.isCorrect(),
                    attempt.getFactorA(),
                    attempt.getFactorB(),
                    attempt.getUser().getId(),
                    attempt.getUser().getAlias()
            );
            ResponseEntity<String> r = restTemplate
                    .postForEntity(gamificationHostUrl + "/attempts", dto, String.class);
            log.info("Gamification service response: {}", r.getStatusCodeValue());
            return r.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("There was a problem sending the attempt.", e);
            return false;
        }
    }
}
