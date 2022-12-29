package microservice.book.gamification.game;

import microservice.book.gamification.game.domain.LeaderBoardRow;
import microservice.book.gamification.game.domain.ScoreCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {
    /**
     * Gets the total score for a given user: the sum of the scores of all
     * their ScoreCards.
     *
     * @param userId the id of the user
     * @return the total score for the user, empty if the user doesn't exist
     */
    @Query("SELECT SUM(s.score) FROM ScoreCard s WHERE s.userId = :userId GROUP BY s.userId")
    Optional<Integer> getTotalScoreForUser(long userId);

    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(long userId);

    @Query("SELECT NEW microservice.book.gamification.game.domain.LeaderBoardRow(s.userId, SUM(s.score)) " +
            "FROM ScoreCard s " +
            "GROUP BY s.userId ORDER BY SUM(s.score) DESC")
    List<LeaderBoardRow> findFirst10();
}
