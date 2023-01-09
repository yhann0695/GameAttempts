package microservice.book.gamification.game;

import lombok.RequiredArgsConstructor;
import microservice.book.gamification.challenge.ChallengeSolvedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attempts")
public class GameController {
    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void postResult(@RequestBody ChallengeSolvedEvent dto) {
        gameService.newAttemptForUser(dto);
    }
}
