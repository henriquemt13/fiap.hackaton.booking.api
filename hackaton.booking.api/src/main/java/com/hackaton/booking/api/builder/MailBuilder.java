package com.hackaton.booking.api.builder;

import com.hackaton.booking.api.domain.dto.MailDTO;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Properties;

public final class MailBuilder {

    private MailBuilder() {
    }

    public static JavaMailSender getJavaMailSender(MailDTO mailDTO) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setDefaultEncoding("utf-8");
        //mailSender.setHost("smtp-mail.outlook.com");
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(mailDTO.getFrom());
        mailSender.setPassword(mailDTO.getPassword());
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

    public static MimeMessage buildMimeMessage(JavaMailSender mailSender, MailDTO mailDTO) throws MessagingException {
        String template = HTMLBuilder.getEmailMessage(mailDTO.getParams(), mailDTO.getClientInfos());
        MimeMessage message = mailSender.createMimeMessage();
        message.setContent(template,"text/html; charset=UTF-8");
        message.setFrom(mailDTO.getFrom());
        message.setSubject(mailDTO.getSubject());
        message.setRecipients(Message.RecipientType.TO, mailDTO.getClientInfos().getEmail());
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(mailDTO.getClientInfos().getEmail());
        helper.setText(template, true);
        return message;
    }


}