package com.janak.FileUploadAndDownload.dto;

import com.janak.FileUploadAndDownload.emuns.EmailType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {
    @Email
    private String toEmail;

    private EmailType emailType;

    @NotNull
    private String subject;

    private String userName;
    private String activationLink;
}
