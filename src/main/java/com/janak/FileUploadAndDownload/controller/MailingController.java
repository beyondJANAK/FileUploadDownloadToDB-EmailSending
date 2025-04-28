package com.janak.FileUploadAndDownload.controller;

import com.janak.FileUploadAndDownload.Producer.RabbitMQEmailSender;
import com.janak.FileUploadAndDownload.dto.EmailDto;
import com.janak.FileUploadAndDownload.dto.MailRequest;
import com.janak.FileUploadAndDownload.service.EmailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailingController {

    private final RabbitMQEmailSender rabbitMQEmailSender;
    private final EmailsService emailsService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> sendMail(@Valid @RequestBody MailRequest mailRequest) {
        rabbitMQEmailSender.sendEmail(mailRequest);
        return ResponseEntity.ok().body("Mail sent successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerEmail(@Valid @RequestBody EmailDto emailDto) {
        emailsService.registerEmail(emailDto);
        return ResponseEntity.ok().body("Email registered successfully");
    }

    @GetMapping("/get-all")
    public List<EmailDto> getAllEmails() {
        return emailsService.getAllEmails();
    }

}
