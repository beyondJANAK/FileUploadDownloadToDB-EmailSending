package com.janak.FileUploadAndDownload.repository;

import com.janak.FileUploadAndDownload.entity.FileSystemStorageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileSystemStorageDataRepository extends JpaRepository<FileSystemStorageData, Integer> {

    Optional<FileSystemStorageData> findByName(String name);

}
