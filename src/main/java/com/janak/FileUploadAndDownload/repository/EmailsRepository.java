package com.janak.FileUploadAndDownload.repository;

import com.janak.FileUploadAndDownload.entity.Emails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailsRepository extends JpaRepository<Emails, Long> {
}
