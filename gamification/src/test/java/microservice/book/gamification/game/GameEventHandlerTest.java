package microservice.book.gamification.game;

import microservice.book.gamification.challenge.ChallengeSolvedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameEventHandlerTest {
    @Mock
    private GameService gameService;

    @InjectMocks
    private GameEventHandler gameEventHandler;

    @BeforeEach
    void setUp() {
        gameEventHandler = new GameEventHandler(gameService);
    }

    @Test
    public void testHandleMultiplicationSolved() {
        ChallengeSolvedEvent event = new ChallengeSolvedEvent(
                1L, true, 20, 10, 1L, "john doe"
        );
        gameEventHandler.handleMultiplicationSolved(event);
        verify(gameService).newAttemptForUser(event);
    }

    @Test
    public void testHandleMultiplicationSolvedError() {
        ChallengeSolvedEvent event = new ChallengeSolvedEvent(
                1L, true, 20, 10, 1L, "john doe"
        );
        Exception exception = new Exception("Error Processing Event");
        Mockito.doAnswer(invocation -> { throw exception; }).when(gameService).newAttemptForUser(event);
        assertThrows(AmqpRejectAndDontRequeueException.class, () -> gameEventHandler.handleMultiplicationSolved(event));
    }
}
