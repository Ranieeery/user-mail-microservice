package dev.raniery.user.producer;

import dev.raniery.user.dto.EmailDTO;
import dev.raniery.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) {
        var emailDTO = new EmailDTO();
        emailDTO.setUserId(userModel.getUserId());
        emailDTO.setEmailTo(userModel.getUserEmail());
        emailDTO.setSubject("Welcome to our platform!");
        emailDTO.setBody(userModel.getUserName() + ", welcome to our platform! \nWe are glad to have you here!");

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }
}
