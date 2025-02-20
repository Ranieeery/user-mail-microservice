package dev.raniery.email.consumer;

import dev.raniery.email.dto.EmailRecordDTO;
import dev.raniery.email.models.EmailModel;
import dev.raniery.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDTO emailDTO) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);

        emailService.sendMail(emailModel);
    }
}
