package microservice.book.gamification.game;

import lombok.RequiredArgsConstructor;
import microservice.book.gamification.game.domain.LeaderBoardRow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaders")
@RequiredArgsConstructor
public class LeaderBoardController {
    private final LeaderBoardService leaderBoardService;

    @GetMapping
    public List<LeaderBoardRow> getLeaderBoard() {
        return leaderBoardService.getCurrentLeaderBoard();
    }
}
