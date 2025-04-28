package com.janak.FileUploadAndDownload.Producer;

import com.janak.FileUploadAndDownload.dto.MailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQEmailSender {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendEmail(MailRequest mailRequest) {
        rabbitTemplate.convertAndSend(exchange, routingKey, mailRequest);
    }
}
