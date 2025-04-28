package com.janak.FileUploadAndDownload.mapper;

import com.janak.FileUploadAndDownload.dto.EmailDto;
import com.janak.FileUploadAndDownload.entity.Emails;
import org.springframework.stereotype.Service;

@Service
public class EmailsMapper {
    public Emails mapToEntity(EmailDto emailDto) {
        Emails emails = new Emails();
        emails.setEmail(emailDto.email());
        emails.setPassword(emailDto.password());
        emails.setEmailType(emailDto.emailType());
        return emails;
    }

    public EmailDto mapToDto(Emails emails) {
        return new EmailDto(
                emails.getEmail(),
                emails.getPassword(),
                emails.getEmailType()
        );
    }
}
