package microservice.book.gamification.game.badgeprocessors;

import microservice.book.gamification.challenge.ChallengeSolvedDTO;
import microservice.book.gamification.game.domain.BadgeType;
import microservice.book.gamification.game.domain.ScoreCard;

import java.util.List;
import java.util.Optional;

public interface BadgeProcessor {
    /**
     * @return the BadgeType object that this processor is handling. You can use
     * it to filter processors according to your needs.
     */
    BadgeType badgeType();

    /**
     * Processes some or all of the passed parameters and decides if the user
     * is entitled to a badge.
     * @return a BadgeType if the user is entitled to this badge, otherwise empty
     */
    Optional<BadgeType> processForOptionalBadge(
            int totalScore,
            List<ScoreCard> scoreCardList,
            ChallengeSolvedDTO solvedChallenge
    );
}
