package com.janak.FileUploadAndDownload.service;


import com.janak.FileUploadAndDownload.entity.FileData;
import com.janak.FileUploadAndDownload.entity.FileSystemStorageData;
import com.janak.FileUploadAndDownload.repository.FileDataRepository;
import com.janak.FileUploadAndDownload.repository.FileSystemStorageDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class FileDataService {

    private final FileDataRepository fileDataRepository;
    private final FileSystemStorageDataRepository fileSystemStorageDataRepository;


    private String FOLDER_PATH = "/home/justjanak/ImageStorage";


    public String uploadImage(MultipartFile file) throws IOException {
        FileData imageData = fileDataRepository.save(FileData.builder().name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressingImage(file.getBytes())).build());

        return "File uploaded successfully";

    }

    public byte[] downloadImage(String fileName) throws IOException {
        FileData imageData = fileDataRepository.findByName(fileName).orElseThrow(() -> new IOException("File not found"));
        return ImageUtils.decompressImage(imageData.getImageData());
    }




    // Image/Videos storage to FileSystem
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + "/" + file.getOriginalFilename();
        FileSystemStorageData fileData = fileSystemStorageDataRepository.save(FileSystemStorageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .path(filePath).build());

        // Store the image to the file
        file.transferTo(new File(filePath));

        return "File saved successfully: " + filePath;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        FileSystemStorageData fileData = fileSystemStorageDataRepository.findByName(fileName).orElseThrow(() -> new IOException("File not found"));
        String filePath = fileData.getPath();
        return Files.readAllBytes(new File(filePath).toPath());
    }

}
