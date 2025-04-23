package com.janak.FileUploadAndDownload.service;

import com.janak.FileUploadAndDownload.dto.MailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailingService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Async
    public void sendMail(MailRequest mailRequest) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", mailRequest.getName());
        context.setVariable("message", mailRequest.getMessage());
        context.setVariable("confirmationLink", mailRequest.getConfirmationLink());

        String htmlContent = templateEngine.process("email-template", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(mailRequest.getToEmail());
        helper.setSubject(mailRequest.getSubject());
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
