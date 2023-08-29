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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Hashtag {

	

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String label;
    
    @CreationTimestamp
    private Timestamp firstUsed;
    
   @CreationTimestamp
    private Timestamp lastUsed;
    
    @ManyToMany
    @JoinTable(
    		name = "tweet_hashtags",
    		joinColumns = @JoinColumn(name = "tweet_id"),
    		inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private List<Tweet> tweets;
    
}
