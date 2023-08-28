package com.cooksys.twitterclone.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet {

    @JoinTable(
            name = "user_mentions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id")
    )

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
