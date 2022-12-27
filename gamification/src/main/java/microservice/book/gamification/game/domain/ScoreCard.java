package microservice.book.gamification.game.domain;

/*
* This class represents the Score linked to an attempt in the game,
* with an associate user and the timestamp in which the score
* is registered.
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreCard {
    public static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue
    private Long cardId;
    private Long userId;
    private Long attemptId;
    @EqualsAndHashCode.Exclude
    private long scoreTimestamp;
    private int score;

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
    }
}
