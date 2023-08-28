package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Tweet {

    @Id
    @GeneratedValue
    @OneToMany
    private Long id;
    private Long author;
    private Long posted;
    private Boolean deleted;
    private String content;
    private Long inReplyTo;
    private Long repostOf;
}
