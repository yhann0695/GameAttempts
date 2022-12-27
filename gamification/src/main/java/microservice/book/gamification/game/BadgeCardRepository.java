package microservice.book.gamification.game;

import microservice.book.gamification.game.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long> {
}
