package com.microservices.resourceserver.service;

import com.microservices.resourceserver.exception.Mp3DataNotFoundException;
import com.microservices.resourceserver.model.Mp3Data;
import com.microservices.resourceserver.model.Mp3Metadata;
import com.microservices.resourceserver.repository.Mp3DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Mp3DataService {

    private final Mp3DataRepository mp3DataRepository;
    private final Mp3MetadataService mp3MetadataService;
    private final RestTemplate restTemplate;

    public Mp3Data save(byte[] mp3Content) {
        Mp3Data mp3Data = mp3DataRepository.save(
                Mp3Data.builder()
                        .content(mp3Content)
                        .build()
        );

        Mp3Metadata metadata = mp3MetadataService.extractMetadata(mp3Data.getContent());
        metadata.setId(mp3Data.getId());

        restTemplate.postForEntity("http://localhost:8081/songs", metadata, Mp3Metadata.class);
        
        return mp3Data;
    }

    public Mp3Data findById(Long id) {
        return mp3DataRepository.findById(id)
                .orElseThrow(() -> new Mp3DataNotFoundException("Mp3 data not found for id: " + id));
    }

    public List<Long> deleteByIds(List<Long> ids) {
        List<Long> existingIds = ids.stream()
                .filter(mp3DataRepository::existsById)
                .toList();

        mp3DataRepository.deleteAllById(existingIds);
        restTemplate.delete("http://localhost:8081/songs?ids={ids}", existingIds);
        
        return List.copyOf(existingIds);
    }
}
