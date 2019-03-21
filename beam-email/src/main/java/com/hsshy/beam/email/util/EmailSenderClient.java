package com.hsshy.beam.email.util;

import org.apache.commons.mail.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Map;

@Component
public class EmailSenderClient {

    //QQ邮箱
    //邮箱中是否开启了这项服务，如果尚未开启，请您在邮箱设置=》帐号=》POP3/IMAP/SMTP/Exchange服务中勾选POP3/SMTP服务和IMAP/SMTP服务；
    //POP3服务器（端口995）	pop.qq.com
    //SMTP服务器（端口465或587）smtp.qq.com

    //163邮箱
    //IMAP服务器  imap.163.com SSL协议端口 993 非SSL协议端口143
    //SMTP服务器  smtp.163.com SSL协议端口 465/994 非SSL协议端口25
    //POP3服务器  pop.163.com  SSL协议端口 995 非SSL协议端口110

    @Value("${email.hostName}")
    private String hostName;//发送端Host
    @Value("${email.smtpPort}")
    private String smtpPort;//发送端口
    @Value("${email.username}")
    private String username;
    @Value("${email.password}")
    private String password;
    @Value("${email.fromAddress}")
    private String fromAddress;//发送邮件的发送地址

    private boolean sslOn = true;

    private static final String DEFAULT_CHARSET = "utf-8";

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public void setSslOn(boolean sslOn) {
        this.sslOn = sslOn;
    }


    public void sendEmail(Email email) throws Exception {
        email.setAuthentication(username, password);
        email.setCharset(DEFAULT_CHARSET);
        email.setFrom(fromAddress);
        email.setSSLOnConnect(sslOn);
        email.setHostName(hostName);
        email.setSmtpPort(new Integer(smtpPort));
        email.send();
    }

    public void sendTextEmail(String targetAddress,String title,String content) throws Exception {
        Email email = new SimpleEmail();
        email.setMsg(content);
        email.setSubject(title);
        email.addTo(targetAddress);
        sendEmail(email);
    }


    /**
     * content为html，此方法将会对html进行转义。
     * @param targetAddress
     * @param title
     * @param content
     * @throws Exception
     */
    public void sendHtmlEmail(String targetAddress,String title,String content) throws Exception {
        HtmlEmail email = new HtmlEmail();
        email.setSubject(title);
        email.setHtmlMsg(content);
        email.addTo(targetAddress);
        sendEmail(email);
    }


    /**
     * 发送多媒体邮件，可以携带附件信息
     * @param urls 附件的URL,key为附件的名称
     */
    public void sendMultipartEmail(String targetAddress,String title,String content,Map<String,URL> urls) throws Exception {
        MultiPartEmail email = new MultiPartEmail();
        for(Map.Entry<String,URL> entry : urls.entrySet()) {
            email.attach(entry.getValue(),entry.getKey(),EmailAttachment.ATTACHMENT);
        }
        email.setSubject(title);
        email.addTo(targetAddress);
        email.setMsg(content);
        sendEmail(email);

    }





}
