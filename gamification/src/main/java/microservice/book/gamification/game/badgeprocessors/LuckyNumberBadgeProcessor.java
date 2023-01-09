package microservice.book.gamification.game.badgeprocessors;

import microservice.book.gamification.challenge.ChallengeSolvedEvent;
import microservice.book.gamification.game.domain.BadgeType;
import microservice.book.gamification.game.domain.ScoreCard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LuckyNumberBadgeProcessor implements BadgeProcessor {
    private static final int LUCKY_NUMBER = 42;

    @Override
    public BadgeType badgeType() {
        return BadgeType.LUCKY_NUMBER;
    }

    @Override
    public Optional<BadgeType> processForOptionalBadge(
            int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedEvent solved
    ) {
        return solved.getFactorA() == LUCKY_NUMBER ||
                solved.getFactorB() == LUCKY_NUMBER ?
                Optional.of(BadgeType.LUCKY_NUMBER) : Optional.empty();
    }
}
