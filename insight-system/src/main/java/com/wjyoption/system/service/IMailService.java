package com.wjyoption.system.service;
import javax.mail.MessagingException;

public interface IMailService {


    /**
     * 简单文本邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param contnet 邮件内容
     */
    public void sendSimpleMail(String to, String subject, String contnet);
    

    /**
     * HTML 文本邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param contnet HTML内容
     * @throws MessagingException
     */
    public void sendHtmlMail(String to, String subject, String contnet) throws MessagingException ;

    /**
     * 附件邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param contnet HTML内容
     * @param filePath 附件路径
     * @throws MessagingException
     */
    public void sendAttachmentsMail(String to, String subject, String contnet,
                                    String filePath) throws MessagingException ;

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
                                       String rscPath, String rscId) ;

}
