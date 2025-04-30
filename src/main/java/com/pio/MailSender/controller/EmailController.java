package com.pio.MailSender.controller;

import com.pio.MailSender.EmailConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailConfiguration emailService;

    public EmailController(EmailConfiguration emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/send")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        boolean isSent = emailService.sendEmail(to, subject, text);
        return isSent ? "Email sent successfully!" : "Failed to send email";
    }
    @PostMapping("/sendWithAttachment")
    public String sendEmailWithAttachment(@RequestParam String to, @RequestParam String subject, @RequestParam String text,@RequestParam String filePath) {
        boolean isSent = emailService.sendEmailWithAttachment(to, subject, text,filePath);
        return isSent ? "Email sent successfully!" : "Failed to send email";
    }
    @PostMapping("/sendWithHTML")
    public String sendEmailWithHtml(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        boolean isSent = emailService.sendEmailWithHTML(to, subject, text);
        return isSent ? "Email sent successfully!" : "Failed to send email";
    }
}
