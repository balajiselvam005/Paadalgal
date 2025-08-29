package com.paadalgal.Paadalgal.controller;

import com.paadalgal.Paadalgal.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping(
            value = "/upload",
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<Object> uploadSong(@RequestParam("file") MultipartFile[] files) {
        try {
            List<String> songUrl = songService.uploadSong(List.of(files));
            return ResponseEntity.ok(songUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload song: " + e.getMessage());
        }
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") Long songId) {
        try {
            String res = songService.getById(songId);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Song not found: " + e.getMessage());
        }
    }

}