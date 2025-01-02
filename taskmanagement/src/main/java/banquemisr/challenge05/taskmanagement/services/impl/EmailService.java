package banquemisr.challenge05.taskmanagement.services.impl;

import banquemisr.challenge05.taskmanagement.models.Notification;
import banquemisr.challenge05.taskmanagement.repositories.NotificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;

    public EmailService(JavaMailSender mailSender, NotificationRepository notificationRepository) {
        this.mailSender = mailSender;
        this.notificationRepository = notificationRepository;
    }

    public void sendEmail(String to, String subject, String body) {
        Notification notification = new Notification();
        notification.setRecipient(to);
        notification.setSubject(subject);
        notification.setMessage(body);
        notification.setSentAt(new Date());
        notification.setStatus("PENDING");
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);
            notification.setStatus("SENT");
            System.out.println("Email sent successfully to " + to);
        } catch (MessagingException e) {
            notification.setStatus("FAILED");
            notification.setFailedAt(new Date());
            notification.setErrorMessage(e.getMessage());

        }
        notificationRepository.save(notification);

    }
}
