package microservice.book.gamification.game.badgeprocessors;

import microservice.book.gamification.challenge.ChallengeSolvedEvent;
import microservice.book.gamification.game.domain.BadgeType;
import microservice.book.gamification.game.domain.ScoreCard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SilverBadgeProcessor implements BadgeProcessor {
    @Override
    public BadgeType badgeType() {
        return BadgeType.SILVER;
    }

    @Override
    public Optional<BadgeType> processForOptionalBadge(
            int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedEvent solved
    ) {
        return currentScore > 150 ? Optional.of(BadgeType.SILVER) : Optional.empty();
    }
}
