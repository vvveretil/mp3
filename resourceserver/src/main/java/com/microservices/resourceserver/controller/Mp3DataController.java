package com.microservices.resourceserver.controller;

import com.microservices.resourceserver.controller.response.UploadMp3DataResponse;
import com.microservices.resourceserver.model.Mp3Data;
import com.microservices.resourceserver.service.Mp3DataService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class Mp3DataController {

    private final Mp3DataService mp3DataService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UploadMp3DataResponse> saveMp3Data(@RequestBody byte[] mp3Content) {
        Mp3Data mp3Data = mp3DataService.save(mp3Content);
        UploadMp3DataResponse response = modelMapper.map(mp3Data, UploadMp3DataResponse.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getMp3Data(@PathVariable Long id) {
        Mp3Data mp3Data = mp3DataService.findById(id);
        return ResponseEntity.ok(mp3Data.getContent());
    }

    @DeleteMapping
    public ResponseEntity<List<Long>> delete(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(
                mp3DataService.deleteByIds(ids)
        );
    }
}
