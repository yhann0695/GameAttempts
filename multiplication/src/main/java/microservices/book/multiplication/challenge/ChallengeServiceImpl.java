package microservices.book.multiplication.challenge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.user.User;
import microservices.book.multiplication.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    private final UserRepository userRepository;
    private final ChallengeAttemptRepository attemptRepository;

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        User user = userRepository.findByAlias(attemptDTO.getUserAlias())
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}", attemptDTO.getUserAlias());
                    return userRepository.save(new User(attemptDTO.getUserAlias()));
                });

        boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();

        ChallengeAttempt checkedAttempt = new ChallengeAttempt(null,
                user,
                attemptDTO.getFactorA(),
                attemptDTO.getFactorB(),
                attemptDTO.getGuess(),
                isCorrect
        );

        ChallengeAttempt storedAttempt = attemptRepository.save(checkedAttempt);

        return storedAttempt;
    }

    @Override
    public List<ChallengeAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
    }


}
