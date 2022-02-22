package uz.pdp.warehouseapp.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void setMailSender(String toEmail,String subject,String body){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("javohirbekrakhimov@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);
        mailSender.send(mailMessage);

    }
}
