package com.janak.FileUploadAndDownload.controller;

import com.janak.FileUploadAndDownload.dto.MailRequest;
import com.janak.FileUploadAndDownload.service.EmailingService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailingController {

    private final EmailingService emailingService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> sendMail(@RequestBody MailRequest mailRequest) throws MessagingException {
        try {
            emailingService.sendMail(mailRequest);
            return ResponseEntity.ok("Email sent successfully!");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }

}
