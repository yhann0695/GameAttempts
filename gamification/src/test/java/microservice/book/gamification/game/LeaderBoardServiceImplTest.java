package microservice.book.gamification.game;

import microservice.book.gamification.game.domain.BadgeCard;
import microservice.book.gamification.game.domain.BadgeType;
import microservice.book.gamification.game.domain.LeaderBoardRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class LeaderBoardServiceImplTest {
    private LeaderBoardService leaderBoardService;

    @Mock
    private ScoreCardRepository scoresRepository;

    @Mock
    private BadgeCardRepository badgeRepository;

    @BeforeEach
    void setUp() {
        leaderBoardService = new LeaderBoardServiceImpl(scoresRepository, badgeRepository);
    }

    @Test
    void retrieveLeaderBoardTest() {
        LeaderBoardRow scoreRow = new LeaderBoardRow(1L, 300L, List.of());
        given(scoresRepository.findFirst10())
                .willReturn(List.of(scoreRow));
        given(badgeRepository.findByUserIdOrderByBadgeTimestampDesc(1L))
                .willReturn(Set.of(new BadgeCard(1L, BadgeType.LUCKY_NUMBER)));

        List<LeaderBoardRow> leaderBoard = leaderBoardService.getCurrentLeaderBoard();

        List<LeaderBoardRow> expectedLeaderBoard = List.of(
                new LeaderBoardRow(
                        1L,
                        300L,
                        List.of(BadgeType.LUCKY_NUMBER.getDescription())
                )
        );

        then(leaderBoard).isEqualTo(expectedLeaderBoard);
    }
}
