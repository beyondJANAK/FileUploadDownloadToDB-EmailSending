package com.janak.FileUploadAndDownload.controller;

import com.janak.FileUploadAndDownload.service.FileDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UploadAndDownloadController {

    private final FileDataService filesDataService;

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadImage = filesDataService.uploadImage(file);
        return ResponseEntity.ok().body(uploadImage);
    }

    @GetMapping("/download-image/{file}")
    public ResponseEntity<?> downloadImage(@PathVariable("file") String file) throws IOException {
        log.info("Downloading image for file: {}", file);
        byte[] imageData = filesDataService.downloadImage(file);
        log.info("Image data length: {}", imageData.length);
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(imageData);
    }
}
