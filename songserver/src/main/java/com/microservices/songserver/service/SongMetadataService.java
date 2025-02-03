package com.microservices.songserver.service;

import com.microservices.songserver.exception.SongMetadataNotFoundException;
import com.microservices.songserver.model.SongMetadata;
import com.microservices.songserver.repository.SongMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongMetadataService {

    private final SongMetadataRepository songMetadataRepository;

    public void save(SongMetadata songMetadata) {
        songMetadataRepository.save(songMetadata);
    }

    public SongMetadata findById(Long id) {
        return songMetadataRepository.findById(id)
                .orElseThrow(() -> new SongMetadataNotFoundException("Song metadata not found for id: " + id));
    }

    public List<Long> deleteByIds(List<Long> ids) {
        List<Long> existingIds = ids.stream()
                .filter(songMetadataRepository::existsById)
                .toList();

        songMetadataRepository.deleteAllById(existingIds);

        return List.copyOf(existingIds);
    }
}
