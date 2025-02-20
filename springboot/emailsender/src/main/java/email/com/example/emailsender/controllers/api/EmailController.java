package email.com.example.emailsender.controllers.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import email.com.example.emailsender.helper.CustomResponse;
import email.com.example.emailsender.helper.EmailRequest;
import email.com.example.emailsender.services.EmailService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
        
        emailService.sendHtmlEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
        return ResponseEntity.ok(
            CustomResponse.builder().message("Email sent successfully").httpStatus(HttpStatus.OK).success(true).build()
        );
    }  
    
    // send with file 

    @PostMapping("/send-with-file")
    public ResponseEntity<CustomResponse> sendWithFile(@RequestPart EmailRequest emailRequest ,@RequestPart MultipartFile file) throws IOException {
        emailService.sendEmailWithAttachment(emailRequest.getTo(),emailRequest.getSubject(), emailRequest.getMessage() ,file.getInputStream());
        return ResponseEntity.ok(
            CustomResponse.builder().message("Email sent successfully").httpStatus(HttpStatus.OK).success(true).build()
        );
    }

}
