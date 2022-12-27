package microservice.book.gamification.game.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BadgeType {
    BRONZE("Bronze"),
    SILVER("Silver"),
    GOLD("Gold"),

    FIRST_WON("First time"),
    LUCKY_NUMBER("Lucky number");

    private final String description;
}
