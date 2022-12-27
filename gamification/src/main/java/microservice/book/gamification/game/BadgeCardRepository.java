package microservice.book.gamification.game;

import microservice.book.gamification.game.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long> {
    /**
     * Retrieves all BadgeCards for a given user.
     *
     * @param userId the id of the user to look for BadgeCards
     * @return the list of BadgeCards, sorted by most recent.
     */
    Set<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(long userId);
}
