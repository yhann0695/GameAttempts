package microservice.book.gamification.game;

import microservice.book.gamification.challenge.ChallengeSolvedDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(GameController.class)
public class GameControllerTest {
    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ChallengeSolvedDTO> json;

    @Test
    void postValidResultTest() throws Exception {
        long userId = 1L, attemptId = 1L;
        var solved = new ChallengeSolvedDTO(
                attemptId, true, 20, 70, userId, "john_doe"
        );

        MockHttpServletResponse response = mvc.perform(
                        post("/attempts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json.write(solved).getJson()))
                .andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
