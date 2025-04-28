package com.janak.FileUploadAndDownload.service;

import com.janak.FileUploadAndDownload.dto.EmailDto;
import com.janak.FileUploadAndDownload.entity.Emails;
import com.janak.FileUploadAndDownload.mapper.EmailsMapper;
import com.janak.FileUploadAndDownload.repository.EmailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailsService {
    private final EmailsMapper emailsMapper;
    private final EmailsRepository emailsRepository;

    public void registerEmail(EmailDto emailDto) {
        emailsRepository.save(emailsMapper.mapToEntity(emailDto));
    }

    public List<EmailDto> getAllEmails() {
        return emailsRepository.findAll().stream().map(emailsMapper::mapToDto).toList();
    }
}
