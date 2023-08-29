package com.cooksys.twitterclone.entities;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String label;
    
    @CreationTimestamp
    private Timestamp firstUsed;
    
   @CreationTimestamp
    private Timestamp lastUsed;
    
    @OneToMany(mappedBy = "hashtag")
    private List<Tweet> tweets;
    
    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;
}
