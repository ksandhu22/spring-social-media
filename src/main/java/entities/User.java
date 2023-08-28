package entities;

import jakarta.persistence.*;

@Entity
@Table(name="TwitterUser")
public class User {

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