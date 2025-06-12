package com.pio.MailSender;

import com.pio.MailSender.configuration.EmailProperties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class EmailConfiguration {
    Logger logger = LoggerFactory.getLogger(EmailConfiguration.class);
    private final EmailProperties emailProperties;
@Autowired
    public EmailConfiguration(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    public boolean sendEmail(String to, String subject, String text) {
        boolean flag = false;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", emailProperties.isSmtpAuth());
        properties.put("mail.smtp.starttls.enable", emailProperties.isStartTls());
        properties.put("mial.smtp.port", emailProperties.getSmtpPort());
        properties.put("mail.smtp.host", emailProperties.getSmtpHost());
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties.getUserName(),emailProperties.getPassword());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setFrom(new InternetAddress(emailProperties.getUserName()));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            flag = true;
        }catch (Exception e) {
            logger.error("Error: {}",e.getMessage());
        }
        return flag;
    }
    public boolean sendEmailWithAttachment(String to, String subject, String text, String file) {
        boolean flag = false;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties.getUserName(), emailProperties.getPassword());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(emailProperties.getUserName()));
            message.setSubject(subject);
            MimeBodyPart part1 = new MimeBodyPart();
            part1.setText(text);
            MimeBodyPart part2 = new MimeBodyPart();
            part2.attachFile(file);
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(part1);
            mimeMultipart.addBodyPart(part2);
            message.setContent(mimeMultipart);
            Transport.send(message);
            flag = true;
        }catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
        }
        return flag;
    }
    public boolean sendEmailWithHTML(String to, String subject, String text) {
        boolean flag = false;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties.getUserName(), emailProperties.getPassword());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setFrom(new InternetAddress(emailProperties.getUserName()));
            message.setSubject(subject);
            message.setContent(text,"text/html");
            Transport.send(message);
            flag = true;
        }catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
        }
        return flag;
    }
}
