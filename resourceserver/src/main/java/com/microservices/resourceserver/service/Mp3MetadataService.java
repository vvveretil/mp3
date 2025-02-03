package com.microservices.resourceserver.service;

import com.microservices.resourceserver.model.Mp3Metadata;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class Mp3MetadataService {

    public Mp3Metadata extractMetadata(byte[] mp3File) {
        try (InputStream inputStream = new ByteArrayInputStream(mp3File)) {
            Metadata metadata = new Metadata();
            AutoDetectParser parser = new AutoDetectParser();
            parser.parse(inputStream, new BodyContentHandler(), metadata, new ParseContext());

            return Mp3Metadata.builder()
                    .name(metadata.get("dc:title"))
                    .artist(metadata.get("xmpDM:artist"))
                    .album(metadata.get("xmpDM:album"))
                    .duration(formatDuration(metadata.get("xmpDM:duration")))
                    .releaseDate(metadata.get("xmpDM:releaseDate"))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract metadata from mp3 file", e);
        }
    }
    
    private String formatDuration(String duration) {
        double totalSeconds = Double.parseDouble(duration);

        int minutes = (int) (totalSeconds / 60);
        int remainingSeconds = (int) (totalSeconds % 60);

        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

}
