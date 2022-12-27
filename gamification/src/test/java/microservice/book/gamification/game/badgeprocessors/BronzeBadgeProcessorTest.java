package microservice.book.gamification.game.badgeprocessors;

import microservice.book.gamification.game.domain.BadgeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BronzeBadgeProcessorTest {
    private BronzeBadgeProcessor bronzeBadgeProcessor;

    @BeforeEach
    void setUp() {
        bronzeBadgeProcessor = new BronzeBadgeProcessor();
    }

    @Test
    void shouldGiveBadgeIfScoreOverThreshold() {
        Optional<BadgeType> badgeType = bronzeBadgeProcessor
                .processForOptionalBadge(60, List.of(), null);
        assertThat(badgeType).contains(BadgeType.BRONZE);
    }

    @Test
    void shouldGiveBadgeIfScoreUnderThreshold() {
        Optional<BadgeType> badgeType = bronzeBadgeProcessor
                .processForOptionalBadge(40, List.of(), null);
        assertThat(badgeType).isEmpty();
    }
}
