package com.microservices.songserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
public class SongMetadata {
    
    @Id
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String duration;
    private String releaseDate;
}
