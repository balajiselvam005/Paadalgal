package com.paadalgal.Paadalgal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/api/song")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadSong(@RequestParam MultipartFile file) {
        String url = songService.uploadSong(file);
        return ResponseEntity<String>
    }
}
