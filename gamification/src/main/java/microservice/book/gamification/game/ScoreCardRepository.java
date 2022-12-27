package microservice.book.gamification.game;

import microservice.book.gamification.game.domain.ScoreCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {
}
