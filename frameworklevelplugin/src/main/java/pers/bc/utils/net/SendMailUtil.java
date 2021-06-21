package pers.bc.utils.net;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import pers.bc.utils.pub.PropertiesUtilbc;
import pers.bc.utils.sql.JdbcSession;

public class SendMailUtil
{
    private String _KEY_SMTP = "key_smtp";
    private String _VALUE_SMTP = "value_smtp";
    private String _KEY_PROPS = "key_props";
    private String _VALUE_PROPS = "value_props";
    private String _SEND_USER = "send_user";
    private String _SEND_UNAME = "send_uname";
    private String _SEND_PWD = "send_pwd";
    // 建立会话
    private MimeMessage message;
    private Session session;
    Map<String, String> mailServerMap = new HashMap<String, String>();

    /*
     * 初始化方泿
     */
    public SendMailUtil()
    {

        URL url = SendMailUtil.class.getResource("MailServer.properties");
        try
        {
            mailServerMap = PropertiesUtilbc.getAllProperties(url.getPath());

            Properties props = System.getProperties();
            props.setProperty(mailServerMap.get(_KEY_SMTP), mailServerMap.get(_VALUE_SMTP));
            props.put(mailServerMap.get(_KEY_PROPS), "true");
            props.put(mailServerMap.get(_KEY_PROPS), "true");
            // props.put("mail.smtp.port", "465");
            session = Session.getDefaultInstance(props, new Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(mailServerMap.get(_SEND_UNAME), mailServerMap.get(_SEND_PWD));
                }
            });
            session.setDebug(true);
            message = new MimeMessage(session);
        }
        catch (IOException e)
        {

        }
    }

    /**
     * *********************************************************** <br>
     * 说明：群叿<br>
     * @see <br>
     * @param headName
     * @param sendHtml
     * @param receiveUser <br>
     * @void <br>
     * @methods pers.bc.utils.net.SendMailUtil#doSendHtmlEmail <br>
     * @author licheng <br>
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @date Created on 2020广朿旿<br>
     * @time 下午12:53:00 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void doSendHtmlEmail(String headName, String sendHtml, String[] receiveUser, File attachment)
            throws MessagingException, UnsupportedEncodingException
    {

        // 发件亿
        InternetAddress from = new InternetAddress(mailServerMap.get(_SEND_USER));
        message.setFrom(from);

        int receiverLen = receiveUser.length;
        // 群发
        for (int i = 0; i < receiverLen; i++)
        {
            InternetAddress toAddr = new InternetAddress(receiveUser[i]);
            message.addRecipient(javax.mail.Message.RecipientType.TO, toAddr);
        }

        // 邮件标题
        message.setSubject(headName);
        String content = sendHtml.toString();
        // 邮件内容,也可以使纯文朿text/plain"
        Transport transport = session.getTransport("smtp");
        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();

        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(sendHtml, "text/html;charset=GBK");
        multipart.addBodyPart(contentPart);

        // 添加附件的内宿
        if (attachment != null)
        {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));

            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞宿
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            // messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) +
            // "?=");

            // MimeUtility.encodeWord可以避免文件名乱砿
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
            multipart.addBodyPart(attachmentBodyPart);
        }

        // 将multipart对象放到message丿
        message.setContent(multipart);
        // 保存邮件
        message.saveChanges();
        transport = session.getTransport("smtp");
        // smtp验证，就是你用来发邮件的邮箱用户名密砿
        transport.connect(mailServerMap.get(_VALUE_SMTP), mailServerMap.get(_SEND_UNAME), mailServerMap.get(_SEND_PWD));
        // 发鿊        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("send success!");
        // // 邮件标题
        // message.setSubject(headName);
        // String content = sendHtml.toString();
        // // 邮件内容,也可以使纯文朿text/plain"
        // message.setContent(content, "text/html;charset=UTF-8");
        // message.saveChanges();
        // Transport transport = session.getTransport("smtp");
        // // smtp验证，就是你用来发邮件的邮箱用户名密砿
        // transport.connect(mailServerMap.get(_VALUE_SMTP), mailServerMap.get(_SEND_UNAME),
        // mailServerMap.get(_SEND_PWD));
        // // 发鿊        // transport.sendMessage(message, message.getAllRecipients());
        // transport.close();

    }

    /**
     * *********************************************************** <br>
     * 说明＿单发<br>
     * @see <br>
     * @param headName
     * @param sendHtml
     * @param receiveUser <br>
     * @void <br>
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @methods pers.bc.utils.sql.SendMailUtil#doSendHtmlEmail <br>
     * @author licheng <br>
     * @date Created on 2020广朿旿<br>
     * @time 下午12:51:57 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void doSendHtmlEmail(String headName, String sendHtml, String receiveUser, File attachment)
            throws MessagingException, UnsupportedEncodingException
    {

        // 发件亿
        InternetAddress from = new InternetAddress(mailServerMap.get(_SEND_USER));
        message.setFrom(from);

        // 单发
        InternetAddress to = new InternetAddress(receiveUser);
        message.setRecipient(Message.RecipientType.TO, to);

        // 邮件标题
        message.setSubject(headName);
        String content = sendHtml.toString();
        // 邮件内容,也可以使纯文朿text/plain"
        Transport transport = session.getTransport("smtp");
        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();

        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(sendHtml, "text/html;charset=GBK");
        multipart.addBodyPart(contentPart);

        // 添加附件的内宿
        if (attachment != null)
        {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));

            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞宿
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            // messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) +
            // "?=");

            // MimeUtility.encodeWord可以避免文件名乱砿
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
            multipart.addBodyPart(attachmentBodyPart);
        }

        // 将multipart对象放到message丿
        message.setContent(multipart);
        // 保存邮件
        message.saveChanges();
        transport = session.getTransport("smtp");
        // smtp验证，就是你用来发邮件的邮箱用户名密砿
        transport.connect(mailServerMap.get(_VALUE_SMTP), mailServerMap.get(_SEND_UNAME), mailServerMap.get(_SEND_PWD));
        // 发鿊        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("send success!");

    }

    public static void main(String[] args)
    {
        try
        {
            SendMailUtil se = new SendMailUtil();
            // 单发
            se.doSendHtmlEmail("宝贝我爱使", "宝贝我爱使", "550583975@yonyou.com", null) ;
            // 群发
            // String[] receiverAddr = {"zhenyxa@yonyou.com", "550583975@qq.com", "wangyuu@yonyou.com"};
            // se.doSendHtmlEmail("宝贝我爱使, "宝贝我爱使, receiverAddr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
