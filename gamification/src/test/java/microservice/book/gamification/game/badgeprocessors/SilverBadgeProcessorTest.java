package microservice.book.gamification.game.badgeprocessors;

import microservice.book.gamification.game.domain.BadgeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SilverBadgeProcessorTest {
    private SilverBadgeProcessor silverBadgeProcessor;

    @BeforeEach
    void setUp() {
        silverBadgeProcessor = new SilverBadgeProcessor();
    }

    @Test
    void shouldGiveBadgeIfScoreOverThreshold() {
        Optional<BadgeType> badgeType = silverBadgeProcessor
                .processForOptionalBadge(151, List.of(), null);
        assertThat(badgeType).contains(BadgeType.SILVER);
    }

    @Test
    void shouldGiveBadgeIfScoreUnderThreshold() {
        Optional<BadgeType> badgeType = silverBadgeProcessor
                .processForOptionalBadge(149, List.of(), null);
        assertThat(badgeType).isEmpty();
    }
}
