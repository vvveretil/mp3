package com.microservices.songserver.controller.response;

import lombok.Data;

@Data
public class GetSongMetadataResponse {
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String duration;
    private String releaseDate;
}
