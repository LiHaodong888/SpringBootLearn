package com.li.springbootmail.test;

import freemarker.template.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MailTest
 * @Author lihaodong
 * @Date 2019/2/24 18:53
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    /**
     * 获取JavaMailSender bean
     */
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 获取配置文件的username
     */
    @Value("${spring.mail.username}")
    private String username;

    /**
     * 发送简单邮件
     */
    @Test
    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        //设定邮件参数
        //发送者
        message.setFrom(username);
        //接收者
        message.setTo("lihaodongmail@163.com");
        //主题
        message.setSubject("测试主题");
        //邮件内容
        message.setText("测试内容");
        // 发送邮件
        javaMailSender.send(message);
    }

    /**
     * 发送附件邮箱
     * @throws Exception
     */
    @Test
    public void sendAttachmentsMail() throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(username);
        helper.setTo("lihaodongmail@163.com");
        helper.setSubject("测试主题：有附件");
        helper.setText("测试内容:有附件的邮件");
        FileSystemResource file = new FileSystemResource(new File("weixin_qrcode.jpg"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);
        javaMailSender.send(mimeMessage);

    }

    /**
     * 发送静态邮箱
     * @throws Exception
     */
    @Test
    public void sendStaticMail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(username);
        helper.setTo("lihaodongmail@163.com");
        helper.setSubject("测试主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:weixin_qrcode\" ></body></html>", true);
        FileSystemResource file = new FileSystemResource(new File("weixin_qrcode.jpg"));
        // addInline函数中资源名称jpg需要与正文中cid:weixin_qrcode对应起来
        helper.addInline("weixin_qrcode", file);
        javaMailSender.send(mimeMessage);

    }

    /**
     * 发送模板信息
     * @throws Exception
     */
    @Test
    public void sendTemplateMail() throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(username);
        helper.setTo("lihaodongmail@163.com");
        helper.setSubject("测试主题：模板邮件");
        /**
         * 模板内需要的参数,保持一致
         */
        Map<String, Object> params = new HashMap<>();
        params.put("userName", "lihaodong");
        /**
         * 配置FreeMarker模板路径
         */
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("mail.ftl"), params);
        helper.setText(html, true);

        javaMailSender.send(mimeMessage);
    }
}
