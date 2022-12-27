package microservices.book.multiplication.challenge;

import microservices.book.multiplication.user.User;
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

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ChallengeAttemptController.class)
public class ChallengeAttemptControllerTest {
    @MockBean
    private ChallengeService challengeService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ChallengeAttemptDTO> jsonRequestAttempt;

    @Autowired
    private JacksonTester<ChallengeAttempt> jsonResultAttempt;

    @Autowired
    private JacksonTester<List<ChallengeAttempt>> jsonResultAttemptList;


    @Test
    void postValidResultTest() throws Exception {
        User user = new User(1L, "John");
        long attemptId = 5L;
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 70, "John", 3500);
        ChallengeAttempt expectedResponse = new ChallengeAttempt(
                attemptId,
                user,
                50,
                70,
                3500,
                true
        );

        given(challengeService.verifyAttempt(eq(attemptDTO))).willReturn(expectedResponse);

        MockHttpServletResponse response = mvc.perform(
                post("/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestAttempt.write(attemptDTO).getJson()))
                .andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonResultAttempt.write(expectedResponse).getJson());
    }

    @Test
    void postInvalidResultTest() throws Exception {
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(2000, -70, "John", 1);

        MockHttpServletResponse response = mvc.perform(
                post("/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestAttempt.write(attemptDTO)
                                .getJson()))
                .andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void getUserStatsTest() throws Exception {
        User user = new User("john_doe");
        ChallengeAttempt attempt1 =
                new ChallengeAttempt(1L, user, 50, 60, 3010, false);
        ChallengeAttempt attempt2 =
                new ChallengeAttempt(2L, user, 50, 60, 3051, false);

        List<ChallengeAttempt> recentAttempts = List.of(attempt1, attempt2);
        given(challengeService.getStatsForUser("john_doe")).willReturn(recentAttempts);

        MockHttpServletResponse response = mvc.perform(get("/attempts")
                .param("alias", "john_doe")).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                jsonResultAttemptList
                        .write(recentAttempts)
                        .getJson()
        );
    }
}
