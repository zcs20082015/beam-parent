package com.hsshy.beam.email.service;

import com.hsshy.beam.email.util.EmailSenderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 发送邮件
 * @author: hs
 * @create: 2019-03-21 16:18:20
 **/
@Service(value = "emailService")
public class EmailService {

    @Autowired
    private EmailSenderClient emailSenderClient;

    //QQ邮箱
    //邮箱中是否开启了这项服务，如果尚未开启，请您在邮箱设置=》帐号=》POP3/IMAP/SMTP/Exchange服务中勾选POP3/SMTP服务和IMAP/SMTP服务；
    //POP3服务器（端口995）	pop.qq.com
    //SMTP服务器（端口465或587）smtp.qq.com

    //163邮箱
    //IMAP服务器  imap.163.com SSL协议端口 993 非SSL协议端口143
    //SMTP服务器  smtp.163.com SSL协议端口 465/994 非SSL协议端口25
    //POP3服务器  pop.163.com  SSL协议端口 995 非SSL协议端口110


    public void sendEmail(String targetAddress){
        try {

            emailSenderClient.sendTextEmail(targetAddress, "测试邮件1", "是否可以收到邮件！");

//            Map<String, URL> attaches = new HashMap<String, URL>();
//            attaches.put("logo", new URL("http://www.baidu.com/img/bd_logo1.png"));
//            emailSenderClient.sendMultipartEmail(targetAddress, "测试邮件", "test", attaches);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
