package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Hashtag {

    @Id
    @GeneratedValue
    @OneToMany
    private Long id;
    private String label;
    private Long firstUsed;
    private Long lastUsed;
}
