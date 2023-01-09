package microservices.book.multiplication.challenge;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChallengeEventPub {
    private final AmqpTemplate amqpTemplate;
    private final String challengesTopicExchange;

    public ChallengeEventPub(
            final AmqpTemplate amqpTemplate,
            @Value("${amqp.exchange.attempts}") final String challengesTopicExchange
    ) {
        this.amqpTemplate = amqpTemplate;
        this.challengesTopicExchange = challengesTopicExchange;
    }

    public void challengeSolved(final ChallengeAttempt challengeAttempt) {
        ChallengeSolvedEvent event = buildEvent(challengeAttempt);
        String routingKey = "attempt." + (event.isCorrect() ? "correct" : "wrong");
        amqpTemplate.convertAndSend(challengesTopicExchange, routingKey, event);
    }

    private ChallengeSolvedEvent buildEvent(final ChallengeAttempt challengeAttempt) {
        return new ChallengeSolvedEvent(
                challengeAttempt.getId(),
                challengeAttempt.isCorrect(),
                challengeAttempt.getFactorA(),
                challengeAttempt.getFactorB(),
                challengeAttempt.getUser().getId(),
                challengeAttempt.getUser().getAlias()
        );
    }
}
