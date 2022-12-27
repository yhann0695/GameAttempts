package microservices.book.multiplication.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ChallengeGeneratorServiceTest {
    private ChallengeGeneratorService challengeGeneratorService;

    @Mock
    private Random random;

    @BeforeEach
    public void setUp() {
        challengeGeneratorService = new ChallengeGeneratorServiceImpl(random);
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimit() {
        given(random.nextInt(89)).willReturn(20, 30);

        Challenge challenge = challengeGeneratorService.randomChallenge();

        then(challenge).isEqualTo(new Challenge(31, 41));
    }
}
