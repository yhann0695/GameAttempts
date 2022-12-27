package microservice.book.gamification.game.badgeprocessors;

import microservice.book.gamification.game.domain.BadgeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GoldBadgeProcessorTest {
    private GoldBadgeProcessor goldBadgeProcessor;

    @BeforeEach
    void setUp() {
        goldBadgeProcessor = new GoldBadgeProcessor();
    }

    @Test
    void shouldGiveBadgeIfScoreOverThreshold() {
        Optional<BadgeType> badgeType = goldBadgeProcessor
                .processForOptionalBadge(401, List.of(), null);
        assertThat(badgeType).contains(BadgeType.GOLD);
    }

    @Test
    void shouldGiveBadgeIfScoreUnderThreshold() {
        Optional<BadgeType> badgeType = goldBadgeProcessor
                .processForOptionalBadge(399, List.of(), null);
        assertThat(badgeType).isEmpty();
    }
}
