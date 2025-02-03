package com.microservices.songserver.controller.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostSongMetadataRequest {
    
    private Long id;
    
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;
    
    @Size(min = 1, max = 100, message = "Artist must be between 1 and 100 characters")
    private String artist;
    
    @Size(min = 1, max = 100, message = "Album must be between 1 and 100 characters")
    private String album;
    
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "Duration must be in the format MM:SS")
    private String duration;
    
    @Pattern(regexp = "\\d{4}", message = "Release date must be in the format YYYY")
    private String releaseDate;
}
