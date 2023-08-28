package entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="TwitterUser")
public class User {

    @JoinTable(
            name = "user_likes",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )

    // User ID used as a PK and FK??? Ask about follower/following id
    @OneToMany
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;

    @Embeddable
    public class Credentials {
        private String username;
        private String password;

    }

    @Embeddable
    public class Profile {
        private Long joined;
        private Boolean deleted;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
    }
}