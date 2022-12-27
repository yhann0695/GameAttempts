package microservice.book.gamification.game;

import lombok.RequiredArgsConstructor;
import microservice.book.gamification.game.domain.LeaderBoardRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderBoardServiceImpl implements LeaderBoardService {
    private final ScoreCardRepository scoreRepository;
    private final BadgeCardRepository badgeRepository;

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        List<LeaderBoardRow> scoreOnly = scoreRepository.findFirst10();

        return scoreOnly.stream().map(row -> {
           List<String> badges = badgeRepository
                   .findByUserIdOrderByBadgeTimestampDesc(row.getUserId())
                   .stream().map(b -> b.getBadgeType().getDescription()).toList();
           return row.withBadges(badges);
        }).collect(Collectors.toList());
    }
}
