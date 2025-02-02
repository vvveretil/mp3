package com.microservices.resourceserver.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mp3Metadata {
        
        private Long id;
        private String name;
        private String artist;
        private String album;
        private String duration;
        private String releaseDate;
}
