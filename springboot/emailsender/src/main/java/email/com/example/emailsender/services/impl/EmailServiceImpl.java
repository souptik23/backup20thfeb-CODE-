package email.com.example.emailsender.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import email.com.example.emailsender.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(String to, String subject, String message) {
        // Validate input
        if (to == null || to.isEmpty()) {
            logger.error("The 'to' address is null or empty.");
            throw new IllegalArgumentException("To address must not be null or empty");
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("suddha810@gmail.com");

        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent successfully to {}", to);
    }

    @Override
    public void sendEmailToAll(String[] to, String subject, String message) {
        // Validate input
        if (to == null || to.length == 0) {
            logger.error("The 'to' addresses array is null or empty.");
            throw new IllegalArgumentException("To addresses must not be null or empty");
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("souptikjee@gmail.com");

        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent successfully to all recipients");
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String message, File file) {
        // Validate input
        if (to == null || to.isEmpty()) {
            logger.error("The 'to' address is null or empty.");
            throw new IllegalArgumentException("To address must not be null or empty");
        }
        if (file == null || !file.exists()) {
            logger.error("Attachment file is null or does not exist.");
            throw new IllegalArgumentException("Attachment file must not be null or non-existent");
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.setFrom("22053201@kiit.ac.in");

            FileSystemResource fileSystemResource = new FileSystemResource(file);
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), file);

            mailSender.send(mimeMessage);
            logger.info("Email with attachment has been sent successfully to {}", to);
        } catch (MessagingException e) {
            logger.error("Failed to send email with attachment", e);
            throw new RuntimeException("Error occurred while sending email with attachment", e);
        }
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        // Validate input
        if (to == null || to.isEmpty()) {
            logger.error("The 'to' address is null or empty.");
            throw new IllegalArgumentException("To address must not be null or empty");
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(htmlContent, true);
            mimeMessageHelper.setFrom("souptikjee@gmail.com");

            mailSender.send(mimeMessage);
            logger.info("HTML email has been sent successfully to {}", to);
        } catch (MessagingException e) {
            logger.error("Failed to send HTML email", e);
            throw new RuntimeException("Error occurred while sending HTML email", e);
        }
    }

    // sending the files via inputstream
    @Override
    public void sendEmailWithAttachment(String to, String subject, String message, InputStream inputStream) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);

            // creating a temp file , and copy everything from the inputstream to the file , then attaching the file in the emaoll by the filesystemresource manager
            File file = new File("src/main/resources/email/test.png");
            Files.copy(inputStream, file.toPath() ,StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),file);
            
            mailSender.send(mimeMessage);
            logger.info("Email with attachment has been sent successfully to {}", to);

        } catch (MessagingException | IOException e) {
            logger.error("Failed to send email with attachment", e);
            throw new RuntimeException("Error occurred while sending email with attachment", e);
        }

    }
}
