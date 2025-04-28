package com.janak.FileUploadAndDownload.dto;

import com.janak.FileUploadAndDownload.emuns.EmailType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EmailDto(@Email String email,
                       @NotNull String password, @NotNull EmailType emailType) {
}
