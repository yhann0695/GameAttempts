package microservice.book.gamification.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.book.gamification.challenge.ChallengeSolvedDTO;
import microservice.book.gamification.game.BadgeCardRepository;
import microservice.book.gamification.game.GameService;
import microservice.book.gamification.game.ScoreCardRepository;
import microservice.book.gamification.game.badgeprocessors.BadgeProcessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final ScoreCardRepository scoreRepository;
    private final BadgeCardRepository badgeCardRepository;
    private final List<BadgeProcessor> badgeProcessors;

    @Override
    public GameResult newAttemptForUser(final ChallengeSolvedDTO challenge) {
        return null;
    }
}
