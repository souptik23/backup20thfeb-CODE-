package email.com.example.emailsender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import email.com.example.emailsender.services.EmailService;

@SpringBootTest
class EmailsenderApplicationTests {

	@Autowired
	private EmailService emailService;

	@Test
	void contextLoads() {
	}

	@Test
	void emailSendTest() {

		System.out.println("Email sent successfully");

		emailService.sendEmail("souptikjee@gmail.com", "checking the spring boot code",
				"hey this is the test email to check the email sender api service");

	}

	// html message sender test

	@Test
	void htmlEmailSendTest() {

		System.out.println("html email has been sent:");
		String html = "" +
				"<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"    <style>" +
				"        body {" +
				"            font-family: Arial, sans-serif;" +
				"            margin: 0;" +
				"            height: 100vh;" +
				"            display: flex;" +
				"            justify-content: center;" +
				"            align-items: center;" +
				"            background: linear-gradient(135deg, #ff9a9e, #fad0c4, #fbc2eb);" +
				"            background-size: 400% 400%;" +
				"            animation: gradientBackground 8s ease infinite;" +
				"        }" +
				"        @keyframes gradientBackground {" +
				"            0% { background-position: 0% 50%; }" +
				"            50% { background-position: 100% 50%; }" +
				"            100% { background-position: 0% 50%; }" +
				"        }" +
				"        .login-container {" +
				"            background: rgba(255, 255, 255, 0.2);" +
				"            border-radius: 15px;" +
				"            box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);" +
				"            backdrop-filter: blur(10px);" +
				"            -webkit-backdrop-filter: blur(10px);" +
				"            padding: 40px;" +
				"            width: 300px;" +
				"            text-align: center;" +
				"        }" +
				"        .login-container h2 {" +
				"            color: white;" +
				"            margin-bottom: 20px;" +
				"            font-size: 28px;" +
				"            animation: fadeIn 2s;" +
				"        }" +
				"        @keyframes fadeIn {" +
				"            from { opacity: 0; }" +
				"            to { opacity: 1; }" +
				"        }" +
				"        .login-container input {" +
				"            width: 100%;" +
				"            padding: 10px;" +
				"            margin: 10px 0;" +
				"            border: none;" +
				"            border-radius: 5px;" +
				"            font-size: 16px;" +
				"        }" +
				"        .login-container button {" +
				"            width: 100%;" +
				"            padding: 10px;" +
				"            background: linear-gradient(90deg, #ff9a9e, #fad0c4);" +
				"            border: none;" +
				"            border-radius: 5px;" +
				"            color: white;" +
				"            font-size: 18px;" +
				"            cursor: pointer;" +
				"            transition: transform 0.2s;" +
				"        }" +
				"        .login-container button:hover {" +
				"            transform: scale(1.05);" +
				"        }" +
				"    </style>" +
				"</head>" +
				"<body>" +
				"    <div class='login-container'>" +
				"        <h2>Login</h2>" +
				"        <form action='www.google.com'>" +
				"            <input type='text' placeholder='Username' required />" +
				"            <input type='password' placeholder='Password' required />" +
				"            <button type='submit'>Sign In</button>" +
				"        </form>" +
				"    </div>" +
				"</body>" +
				"</html>";

		emailService.sendHtmlEmail("souptikkaran1@gmail.com", "checking the spring boot code", html);
	}

	// send email with attachment
	@Test
	void semdEmailWithAttachment() {
		System.out.println("Email with attachment has been sent");
		emailService.sendEmailWithAttachment("souptikkaran1@gmail.com",
				"checking the spring boot code",
				"hey this is the test email to check the email sender api service",
				new File("src\\main\\resources\\static\\Screenshot 2025-01-21 000638.png"));
	}

	@Test
	void semdEmailWithAttachmentWithStream() {

		File file = new File("src\\main\\resources\\static\\Screenshot 2025-01-21 000638.png");
		try {
			InputStream inputStream = new FileInputStream(file);
			emailService.sendEmailWithAttachment("souptikkaran1@gmail.com",
					"checking the spring boot code",
					"hey this is the test email to check the email sender api service",
					inputStream
					);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Email with attachment has been sent");

	}
}
