package com.wjyoption.system.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.wjyoption.system.service.IMailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.util.Properties;

@Service
public class MailServiceImpl implements IMailService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;// = "752873241@qq.com";

    @Autowired
    private JavaMailSender mailSender;
    
//    @Autowired MailConfiguration mailConfiguration;

    /**
     * 简单文本邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param contnet 邮件内容
     */
    public void sendSimpleMail(String to, String subject, String contnet){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(contnet);
        message.setFrom(from);
//        mailConfiguration.JavaMailSender().send(message);
        getMailSender().send(message);
    }
    
    private static JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        //链接服务器
        javaMailSender.setHost("smtp.qq.com");
        //账号
        javaMailSender.setUsername("752873241@qq.com");
        //密码
        javaMailSender.setPassword("ounppqekfhbkbfff");
        javaMailSender.setDefaultEncoding("UTF-8");

        Properties properties = new Properties();
        //设置链接超时
        properties.setProperty("mail.smtp.timeout", "1000");
        //设置通过ssl协议使用465端口发送、使用默认端口（25）时下面三行不需要
        //开启认证
        properties.setProperty("mail.smtp.auth", "true");
        //设置ssl端口
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    /**
     * HTML 文本邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param contnet HTML内容
     * @throws MessagingException
     */
    public void sendHtmlMail(String to, String subject, String contnet) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(contnet, true);
        helper.setFrom(from);

        mailSender.send(message);
    }

    /**
     * 附件邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param contnet HTML内容
     * @param filePath 附件路径
     * @throws MessagingException
     */
    public void sendAttachmentsMail(String to, String subject, String contnet,
                                    String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(contnet, true);
        helper.setFrom(from);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = file.getFilename();
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }

    /**
     * 图片邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param contnet HTML内容
     * @param rscPath 图片路径
     * @param rscId 图片ID
     * @throws MessagingException
     */
    public void sendInlinkResourceMail(String to, String subject, String contnet,
                                       String rscPath, String rscId) {
        logger.info("发送静态邮件开始: {},{},{},{},{}", to, subject, contnet, rscPath, rscId);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {

            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(contnet, true);
            helper.setFrom(from);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            mailSender.send(message);
            logger.info("发送静态邮件成功!");

        } catch (MessagingException e) {
            logger.info("发送静态邮件失败: ", e);
        }

    }

}
