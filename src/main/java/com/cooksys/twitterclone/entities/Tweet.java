package com.cooksys.twitterclone.entities;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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
    
    private Long id;
    
    private Long author;
    
    @CreationTimestamp
    private Timestamp posted;
    private Boolean deleted;
    private String content;
    private Long inReplyTo;
    private Long repostOf;
    
    @OneToMany(mappedBy = "tweet")
    private List<Hashtag> hashtags;
    
    @ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
	@JoinColumn(name = "hashtag")
    private Hashtag hashtag;
}
