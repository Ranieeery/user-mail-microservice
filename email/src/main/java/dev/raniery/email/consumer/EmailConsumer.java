package dev.raniery.email.consumer;

import dev.raniery.email.dto.EmailRecordDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDTO emailDTO) {
        System.out.println("Email: " + emailDTO.emailTo());
    }
}
