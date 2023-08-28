package com.cooksys.twitterclone.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HashtagDto {

    private Long id;
    private String label;
    private Long firstUsed;
    private Long lastUsed;
}
