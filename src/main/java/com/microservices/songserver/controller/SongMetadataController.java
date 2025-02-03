package com.microservices.songserver.controller;

import com.microservices.songserver.controller.request.PostSongMetadataRequest;
import com.microservices.songserver.controller.response.GetSongMetadataResponse;
import com.microservices.songserver.controller.response.PostSongMetadataResponse;
import com.microservices.songserver.model.SongMetadata;
import com.microservices.songserver.service.SongMetadataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongMetadataController {

    private final SongMetadataService songMetadataService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PostSongMetadataResponse> save(@Valid @RequestBody PostSongMetadataRequest request) {
        SongMetadata songMetadata = modelMapper.map(request, SongMetadata.class);

        songMetadataService.save(songMetadata);

        return ResponseEntity.ok(
                modelMapper.map(songMetadata, PostSongMetadataResponse.class)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSongMetadataResponse> get(@PathVariable Long id) {
        SongMetadata songMetadata = songMetadataService.findById(id);

        return ResponseEntity.ok(
                modelMapper.map(songMetadata, GetSongMetadataResponse.class)
        );
    }

    @DeleteMapping
    public ResponseEntity<List<Long>> delete(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(
                songMetadataService.deleteByIds(ids)
        );
    }
}
