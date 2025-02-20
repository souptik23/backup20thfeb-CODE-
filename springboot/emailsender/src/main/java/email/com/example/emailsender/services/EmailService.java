package email.com.example.emailsender.services;

import java.io.File;
import java.io.InputStream;

public interface EmailService {

    //send email to single recipient
    void sendEmail(String to, String subject, String message);

    //send email to multiple recipients
    void sendEmailToAll(String[] to, String subject, String message);

    //send email with attachment
    void sendEmailWithAttachment(String to, String subject, String message, File file);

    //send email with html content
    void sendHtmlEmail(String to, String subject, String htmlContent);

    //send data via input stream
    void sendEmailWithAttachment(String to, String subject, String message, InputStream inputStream);
}
