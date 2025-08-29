package com.paadalgal.Paadalgal.service;

import com.paadalgal.Paadalgal.model.Song;
import com.paadalgal.Paadalgal.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SongService {

    private final CloudinaryService cloudinaryService;
    private final SongRepository songRepository;

    public SongService(CloudinaryService cloudinaryService, SongRepository songRepository) {
        this.cloudinaryService = cloudinaryService;
        this.songRepository = songRepository;
    }

    public List<String> uploadSong(List<MultipartFile> files) throws IOException {
        List<String> songUrls = new ArrayList<>();

        for(MultipartFile file : files) {

        Map<String, Object> uploadResult = cloudinaryService.uploadFile(file);
        String url = (String) uploadResult.get("secure_url");

        songUrls.add(url);

        String publicId = (String) uploadResult.get("public_id");
        String originalFilename = file.getOriginalFilename();
        String title = originalFilename != null ?
                originalFilename.substring(0, originalFilename.lastIndexOf('.')) : "Unknown";


        String duration = uploadResult.get("duration") != null ?
                String.valueOf(uploadResult.get("duration")) : "Unknown";


        Song song = new Song();
        song.setTitle(title);
        song.setSongUrl(url);
        song.setDuration(duration);

        songRepository.save(song);
        }

        return songUrls;
    }
    public String getById(Long songId) {
        return songRepository.findById(songId)
                .map(Song::getSongUrl)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + songId));
    }

}