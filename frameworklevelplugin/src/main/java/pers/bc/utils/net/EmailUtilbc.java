package pers.bc.utils.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import pers.bc.utils.pub.JsonUtilbc;
import pers.bc.utils.pub.PropertiesUtilbc;
import pers.bc.utils.pub.PubEnvUtilbc;

public class EmailUtilbc
{
    public static EmailCons emailCons;
    private Map<String, Object> mailMap = new HashMap<String, Object>();
    // 建立会话
    private MimeMessage message;
    private Session session;
    
    /* 初始化方法 */
    public EmailUtilbc(String emailType)
    {
        URL url = EmailUtilbc.class.getResource("MailServer.properties");
        try
        {
            Map<String, String> mailAllMap = PropertiesUtilbc.getAllProperties(url.getPath());
            mailMap = JsonUtilbc.toMap(mailAllMap.get(emailType));
            Properties props = System.getProperties();
            // 开启debug调试
            // 开启debug调试
            props.setProperty("mail.debug", (String) mailMap.get(EmailCons._EMAIL_DEBUG));
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", (String) mailMap.get(EmailCons._EMAIL_AUTH));
            // 设置邮件服务器主机名
            props.setProperty("mail.smtp.host", (String) mailMap.get(EmailCons._EMAIL_HOST));
            // 端口号
            if ("true".equals(mailMap.get(EmailCons._EMAIL_ISPORT))) props.put("mail.smtp.port", mailMap.get(EmailCons._EMAIL_PORT));
            // 发送邮件协议名称
            props.setProperty("mail.transport.protocol", (String) mailMap.get(EmailCons._EMAIL_PROTOCOL));
            
            session = Session.getDefaultInstance(props, new Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication((String) mailMap.get(EmailCons._SEND_UNAME),
                        (String) mailMap.get(EmailCons._SEND_PWD));
                }
            });
            session.setDebug(true);
            // 获取邮件对象
            // 发送的消息，基于观察者模式进行设计的
            message = new MimeMessage(session);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 单发邮件<br>
     * @see <br>
     * @param receiveUser 接受人
     * @param mailTitle 邮件标题
     * @param mailContent 邮件内容
     * @throws MessagingException
     * @throws UnsupportedEncodingException <br>
     * @void <br>
     * @methods pers.bc.utils.net.EmailUtilbc#doSendHtmlEmail <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午5:37:15 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void doSendHtmlEmail(String receiveUser, String mailTitle, Object mailContent)
            throws MessagingException, UnsupportedEncodingException
    {
        doSendHtmlEmail(receiveUser, mailTitle, mailContent, null);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 单发邮件<br>
     * @see <br>
     * @param receiveUser 接受人
     * @param mailTitle 邮件标题
     * @param mailContent 邮件内容
     * @param attachment 附件
     * @throws MessagingException
     * @throws UnsupportedEncodingException <br>
     * @void <br>
     * @methods pers.bc.utils.net.EmailUtilbc#doSendHtmlEmail <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午5:36:06 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void doSendHtmlEmail(String receiveUser, String mailTitle, Object mailContent, File attachment)
            throws MessagingException, UnsupportedEncodingException
    {
        doSendHtmlEmail(new String[]{receiveUser}, mailTitle, mailContent, attachment);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 群发<br>
     * @see <br>
     * @param receiveUser 接受人
     * @param mailTitle 邮件标题
     * @param mailContent 邮件内容
     * @throws MessagingException
     * @throws UnsupportedEncodingException <br>
     * @void <br>
     * @methods pers.bc.utils.net.EmailUtilbc#doSendHtmlEmail <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午5:35:33 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void doSendHtmlEmail(String[] receiveUser, String mailTitle, Object mailContent)
            throws MessagingException, UnsupportedEncodingException
    {
        doSendHtmlEmail(receiveUser, mailTitle, mailContent, null);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 群发<br>
     * @see <br>
     * @param receiveUser 接受人
     * @param mailTitle 邮件标题
     * @param mailContent 邮件内容
     * @param attachment 附件
     * @throws MessagingException
     * @throws UnsupportedEncodingException <br>
     * @void <br>
     * @methods pers.bc.utils.net.EmailUtilbc#doSendHtmlEmail <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午5:35:33 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void doSendHtmlEmail(String[] receiveUser, String mailTitle, Object mailContent, File attachment)
            throws MessagingException, UnsupportedEncodingException
    {
        executeSendEmail(receiveUser, mailTitle, mailContent, attachment);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：执行邮件发送 <br>
     * @see <br>
     * @param receiveUser 接受人
     * @param mailTitle 邮件标题
     * @param mailContent 邮件内容
     * @param attachment 附件
     * @throws MessagingException
     * @throws UnsupportedEncodingException <br>
     * @void <br>
     * @methods pers.bc.utils.net.EmailUtilbc#executeSendEmail <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午5:39:19 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private void executeSendEmail(String[] receiveUser, String mailTitle, Object mailContent, File attachment)
            throws MessagingException, UnsupportedEncodingException
    {
        
        // 设置发件人邮箱
        InternetAddress from = new InternetAddress((String) mailMap.get(EmailCons._SEND_USER), "你好！", "UTF-8");
        message.setFrom(from);
        // 收件人
        for (int i = 0, j = PubEnvUtilbc.getSize(receiveUser); i < j; i++)
        {
            InternetAddress toAddr = new InternetAddress(receiveUser[i]);
            message.addRecipient(javax.mail.Message.RecipientType.TO, toAddr);
        }
        // 邮件标题
        message.setSubject(mailTitle);
        // 设置显示的发件时间
        message.setSentDate(new Date());
        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();
        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(mailContent, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        // 添加附件的内容
        if (attachment != null)
        {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            
            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            // messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) +
            // "?=");
            
            // MimeUtility.encodeWord(attachment.getName());可以避免文件名乱码
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
            multipart.addBodyPart(attachmentBodyPart);
        }
        // 将multipart对象放到message中
        message.setContent(multipart);
        // 保存邮件
        message.saveChanges();
        // 得到邮差对象
        // 邮件内容,也可以使纯文本"text/plain"
        Transport transport = session.getTransport((String) mailMap.get(EmailCons._EMAIL_PROTOCOL));
        // smtp验证，就是你用来发邮件的邮箱用户名密码
        transport.connect((String) mailMap.get(EmailCons._EMAIL_HOST), (String) mailMap.get(EmailCons._SEND_UNAME),
            (String) mailMap.get(EmailCons._SEND_PWD));
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 邮件单发（自由编辑短信，并发送，适用于私信）
     * @param toEmailAddress 收件箱地址
     * @param emailTitle 邮件主题
     * @param emailContent 邮件内容
     * @throws Exception
     * @void <br>
     * @methods pers.bc.utils.net.EmailUtilbc#sendEmail <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午6:23:51 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void sendEmail(String toEmailAddress, String emailTitle, String emailContent) throws Exception
    {
        
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", (String) mailMap.get(EmailCons._EMAIL_DEBUG));
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", (String) mailMap.get(EmailCons._EMAIL_AUTH));
        // 设置邮件服务器主机名
        props.setProperty("mail.smtp.host", (String) mailMap.get(EmailCons._EMAIL_HOST));
        // 端口号
        if ("true".equals(mailMap.get(EmailCons._EMAIL_ISPORT))) props.put("mail.smtp.port", mailMap.get(EmailCons._EMAIL_PORT));
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", (String) mailMap.get(EmailCons._EMAIL_PROTOCOL));
        
        // /** SSL认证，注意腾讯邮箱是基于SSL加密的，所以需要开启才可以使用 **/
        // MailSSLSocketFactory sf = new MailSSLSocketFactory();
        // sf.setTrustAllHosts(true);
        
        // 设置是否使用ssl安全连接（一般都使用）
        // props.put("mail.smtp.ssl.enable", "true");
        // props.put("mail.smtp.ssl.socketFactory", sf);
        // 创建会话
        Session session = Session.getInstance(props);
        // 获取邮件对象
        // 发送的消息，基于观察者模式进行设计的
        Message msg = new MimeMessage(session);
        // 设置邮件标题
        msg.setSubject(emailTitle);
        // 设置邮件内容
        // 使用StringBuilder，因为StringBuilder加载速度会比String快，而且线程安全性也不错
        StringBuilder builder = new StringBuilder();
        // 写入内容
        builder.append("\n" + emailContent);
        // 写入我的官网
        builder.append("\n官网：" + "licheng");
        // 定义要输出日期字符串的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 在内容后加入邮件发送的时间
        builder.append("\n时间：" + sdf.format(new Date()));
        // 设置显示的发件时间
        msg.setSentDate(new Date());
        // 设置邮件内容
        msg.setText(builder.toString());
        // 设置发件人邮箱
        // InternetAddress 的三个参数分别为: 发件人邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
        msg.setFrom(new InternetAddress((String) mailMap.get(EmailCons._SEND_USER), "你好！", "UTF-8"));
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        // 密码不是自己QQ邮箱的密码，而是在开启SMTP服务时所获取到的授权码
        // connect(host, user, password)
        transport.connect((String) mailMap.get(EmailCons._EMAIL_HOST), (String) mailMap.get(EmailCons._SEND_UNAME),
            (String) mailMap.get(EmailCons._SEND_PWD));
        // 发送邮件
        transport.sendMessage(msg, new Address[]{new InternetAddress(toEmailAddress)});
        // 将该邮件保存到本地
        OutputStream out = new FileOutputStream("MyEmail.eml");
        msg.writeTo(out);
        out.flush();
        out.close();
        
        transport.close();
    }
    
    public interface EmailCons
    {
        String EMAIL_163 = "email_163";
        String EMAIL_QQ = "email_qq";
        String EMAIL_YONYOU = "email_yonyou";
        String EMAIL_LIVE = "email_live";
        /////////////////////////////////////
        
        String _EMAIL_HOST = "email_host";
        String _EMAIL_AUTH = "email_auth";
        String _EMAIL_PORT = "email_port";
        String _EMAIL_ISPORT = "email_isport";
        String _EMAIL_PROTOCOL = "email_protocol";
        
        String _EMAIL_DEBUG = "email_debug";
        
        String _SEND_USER = "send_user";
        String _SEND_UNAME = "send_uname";
        String _SEND_PWD = "send_pwd";// 表示邮箱第三方服务器的授权码，而不是邮箱的登录密码！
    }
}
