package entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Hashtag {

    @JoinTable(
            name = "tweet_hastags",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )

    @Id
    @GeneratedValue
    @OneToMany
    private Long id;
    private String label;
    private Long firstUsed;
    private Long lastUsed;
}
