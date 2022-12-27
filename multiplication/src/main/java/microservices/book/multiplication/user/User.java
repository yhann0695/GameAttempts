package microservices.book.multiplication.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String alias;

    public User(final String userAlias) {
        this(null, userAlias);
    }
}
