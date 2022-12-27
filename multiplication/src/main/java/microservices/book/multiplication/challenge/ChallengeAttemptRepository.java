package microservices.book.multiplication.challenge;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {

    /*
    * @return the last 10 attempts for a given user, identified by their alias.
    * */
    List<ChallengeAttempt> findTop10ByUserAliasOrderByIdDesc(String alias);
}
