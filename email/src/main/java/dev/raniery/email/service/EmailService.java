package dev.raniery.email.service;

import dev.raniery.email.enums.StatusEmail;
import dev.raniery.email.models.EmailModel;
import dev.raniery.email.repository.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;
    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = javaMailSender;
    }

    @Transactional
    public EmailModel sendMail(EmailModel emailModel) {
        try {
            emailModel.setSendDate(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(emailModel.getEmailTo());
            msg.setSubject(emailModel.getSubject());
            msg.setText(emailModel.getBody());
            mailSender.send(msg);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }

    }
}
