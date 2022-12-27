package microservice.book.gamification.game.badgeprocessors;

import microservice.book.gamification.challenge.ChallengeSolvedDTO;
import microservice.book.gamification.game.domain.BadgeType;
import microservice.book.gamification.game.domain.ScoreCard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BronzeBadgeProcessor implements BadgeProcessor {
    @Override
    public BadgeType badgeType() {
        return BadgeType.BRONZE;
    }

    @Override
    public Optional<BadgeType> processForOptionalBadge(
            int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDTO solved
    ) {
        return currentScore > 50 ? Optional.of(BadgeType.BRONZE) : Optional.empty();
    }
}
