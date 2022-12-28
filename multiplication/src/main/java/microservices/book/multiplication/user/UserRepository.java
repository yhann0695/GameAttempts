package microservices.book.multiplication.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAlias(final String alias);

    List<User> findAllByIdIn(final List<Long> idList);
}
