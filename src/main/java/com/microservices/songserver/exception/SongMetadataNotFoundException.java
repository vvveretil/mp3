package com.microservices.songserver.exception;

public class SongMetadataNotFoundException extends RuntimeException {
    public SongMetadataNotFoundException(String message) {
        super(message);
    }
}
