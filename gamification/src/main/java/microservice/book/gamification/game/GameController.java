package microservice.book.gamification.game;

import lombok.RequiredArgsConstructor;
import microservice.book.gamification.challenge.ChallengeSolvedDTO;
import microservice.book.gamification.game.GameService.GameResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attempts")
public class GameController {
    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void postResult(@RequestBody ChallengeSolvedDTO dto) {
        gameService.newAttemptForUser(dto);
    }
}
