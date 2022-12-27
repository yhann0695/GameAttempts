package microservice.book.gamification.game;

import microservice.book.gamification.challenge.ChallengeSolvedDTO;
import microservice.book.gamification.game.domain.BadgeType;

import java.util.List;

public interface GameService {

    /*
    * Process a new attempt from a given user.
    * @param challenge the challenge data with user details, factors, etc.
    * @return a {@link GameResult} object containing the new score and badge cards obtained
    */
    GameResult newAttemptForUser(ChallengeSolvedDTO challenge);

    class GameResult {
        int score;
        List<BadgeType> badges;
    }
}
