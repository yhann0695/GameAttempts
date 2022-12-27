package microservice.book.gamification.game;

import microservice.book.gamification.challenge.ChallengeSolvedDTO;
import microservice.book.gamification.game.GameService.GameResult;
import microservice.book.gamification.game.badgeprocessors.BadgeProcessor;
import microservice.book.gamification.game.domain.BadgeCard;
import microservice.book.gamification.game.domain.BadgeType;
import microservice.book.gamification.game.domain.ScoreCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {
    private GameService gameService;

    @Mock
    private ScoreCardRepository scoreCardRepository;

    @Mock
    private BadgeCardRepository badgeCardRepository;

    @Mock
    private BadgeProcessor badgeProcessors;

    @BeforeEach
    void setUp() {
        gameService = new GameServiceImpl(
                scoreCardRepository,
                badgeCardRepository,
                List.of(badgeProcessors)
        );
    }

    @Test
    void processCorrectAttemptTest() {
        long userId = 1L, attemptId = 10L;
        var attempt =
                new ChallengeSolvedDTO(attemptId, true, 20, 70, userId, "john_doe");
        ScoreCard score =  new ScoreCard(userId, attemptId);

        given(scoreCardRepository.getTotalScoreForUser(userId))
                .willReturn(Optional.of(10));

        given(scoreCardRepository.findByUserIdOrderScoreTimestampDesc(userId))
                .willReturn(List.of(score));

        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId))
                .willReturn(Set.of(new BadgeCard(userId, BadgeType.FIRST_WON)));

        given(badgeProcessors.badgeType())
                .willReturn(BadgeType.LUCKY_NUMBER);

        given(badgeProcessors.processForOptionalBadge(10, List.of(score), attempt))
                .willReturn(Optional.of(BadgeType.LUCKY_NUMBER));

        final GameResult gameResult = gameService.newAttemptForUser(attempt);

        then(gameResult)
                .isEqualTo(new GameResult(10, List.of(BadgeType.LUCKY_NUMBER)));

        verify(scoreCardRepository).save(score);
        verify(badgeCardRepository).saveAll(List.of(new BadgeCard(userId, BadgeType.LUCKY_NUMBER)));
    }

    @Test
    void processWrongAttemptTest() {
        GameResult gameResult = gameService.newAttemptForUser(
                new ChallengeSolvedDTO(10L, false, 10, 10, 1L, "john_doe")
        );

        then(gameResult).isEqualTo(new GameResult(0, List.of()));
    }
}
