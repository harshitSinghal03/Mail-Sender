package com.pio.MailSender.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class EmailProperties {
    @Value("${EMAIL_USERNAME:demou8467@gmail.com}")
    private String userName;
    @Value("${EMAIL_PASSWORD:ijpvocxhazjfikfb}")
    private String password;
    @Value("${email.smtp.host}")
    private String smtpHost;
    @Value("${email.smtp.port}")
    private int smtpPort;
    @Value("${email.smtp.auth}")
    private boolean smtpAuth;
    @Value("${email.smtp.starttls}")
    private boolean startTls;
}
