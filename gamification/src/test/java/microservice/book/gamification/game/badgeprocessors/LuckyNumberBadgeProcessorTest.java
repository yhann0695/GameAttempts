package microservice.book.gamification.game.badgeprocessors;

import microservice.book.gamification.challenge.ChallengeSolvedEvent;
import microservice.book.gamification.game.domain.BadgeType;
import microservice.book.gamification.game.domain.ScoreCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LuckyNumberBadgeProcessorTest {
    private LuckyNumberBadgeProcessor luckyNumberBadgeProcessor;

    @BeforeEach
    void setUp() {
        luckyNumberBadgeProcessor = new LuckyNumberBadgeProcessor();
    }

    @Test
    void shouldGiveBadgeIfLuckyFactor() {
        Optional<BadgeType> badgeType = luckyNumberBadgeProcessor
                .processForOptionalBadge(
                        10,
                        List.of(new ScoreCard(1L, 1L)),
                        new ChallengeSolvedEvent(1L,
                                true,
                                42,
                                10,
                                1L,
                                "john_doe"
                        )
                );
        assertThat(badgeType).contains(BadgeType.LUCKY_NUMBER);
    }

    @Test
    void shouldNotGiveBadgeIfNotLuckyFactor() {
        Optional<BadgeType> badgeType = luckyNumberBadgeProcessor
                .processForOptionalBadge(10, List.of(new ScoreCard(1L, 1L)),
                        new ChallengeSolvedEvent(1L,
                                true,
                                22,
                                10,
                                1L,
                                "john_doe"
                        )
                );
        assertThat(badgeType).isEmpty();
    }
}
