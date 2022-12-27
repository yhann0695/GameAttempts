package microservice.book.gamification.game.badgeprocessors;

import microservice.book.gamification.game.domain.BadgeType;
import microservice.book.gamification.game.domain.ScoreCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstWonBadgeProcessorTest {
    private FirstWonBadgeProcessor firstWonBadgeProcessor;

    @BeforeEach
    void setUp() {
        firstWonBadgeProcessor = new FirstWonBadgeProcessor();
    }

    @Test
    void shouldGiveBadgeIfFirstTime() {
        Optional<BadgeType> badgeType = firstWonBadgeProcessor
                .processForOptionalBadge(
                        10,
                        List.of(new ScoreCard(1L, 1L)),
                        null);
        assertThat(badgeType).contains(BadgeType.FIRST_WON);
    }

    @Test
    void shouldNotGiveBadgeIfNotFirstTime() {
        Optional<BadgeType> badgeType = firstWonBadgeProcessor
                .processForOptionalBadge(
                        20,
                        List.of(new ScoreCard(1L, 1L),
                                new ScoreCard(1L, 2L)),
                        null);
        assertThat(badgeType).isEmpty();
    }
}
