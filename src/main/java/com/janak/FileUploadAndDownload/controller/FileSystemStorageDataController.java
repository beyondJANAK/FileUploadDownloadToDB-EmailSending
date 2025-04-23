package com.janak.FileUploadAndDownload.controller;

import com.janak.FileUploadAndDownload.service.FileDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class FileSystemStorageDataController {
    private final FileDataService fileDataService;

    @PostMapping("/upload-file-system")
    public ResponseEntity<?> uploadFileSystemData(@RequestParam MultipartFile file) throws IOException {
        String uploadImage = fileDataService.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/download-file-system/{file}")
    public ResponseEntity<?> downloadFileSystemData(@PathVariable String file) throws IOException {
        byte[] imageData = fileDataService.downloadImageFromFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }

}
