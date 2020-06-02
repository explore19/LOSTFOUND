package com.lost_found.service.Impl;

import com.lost_found.VO.MailVO;
import com.lost_found.common.ServerResponse;
import com.lost_found.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.Date;

@Service
public class MailServiceImpl implements MailService
{
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public ServerResponse sendMail(MailVO mailVO)
    {
        ServerResponse serverResponse = checkMail(mailVO);
        if (serverResponse.isSuccess())
        {
            ServerResponse serverResponse1 = sendMimeMail(mailVO);
            if (serverResponse1.isSuccess())
                return ServerResponse.createBySuccessMessage("发送成功");
        }
        return ServerResponse.createByErrorMessage("发送失败");
    }
    @Override
    public ServerResponse checkMail(MailVO mailVO)
    {
        if (StringUtils.isEmpty(mailVO.getText()))
            return ServerResponse.createByErrorMessage("内容不能为空");
        if (StringUtils.isEmpty(mailVO.getTo()))
            return ServerResponse.createByErrorMessage("收信人不能为空");
        if (StringUtils.isEmpty(mailVO.getSubject()))
            return ServerResponse.createByErrorMessage("主题不能为空");

        return ServerResponse.createBySuccessMessage("邮件检测通过");
    }

    @Async
    @Override
    public ServerResponse sendMimeMail(MailVO mailVO)
    {
        MimeMessageHelper messageHelper = null;
        try
        {
            messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            messageHelper.setFrom(mailVO.getFrom());
            messageHelper.setTo(mailVO.getTo());
            messageHelper.setSubject(mailVO.getSubject());
            messageHelper.setText(mailVO.getText());

            if (!StringUtils.isEmpty(mailVO.getCc()))
            {
                messageHelper.setCc(mailVO.getCc().split(","));
            }
            if (!StringUtils.isEmpty(mailVO.getBcc()))
            {//密送
                messageHelper.setCc(mailVO.getBcc().split(","));
            }
            if (mailVO.getMultipartFiles() != null) {//添加邮件附件
            for (MultipartFile multipartFile : mailVO.getMultipartFiles())
            {
                messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
            }
        }
            if (StringUtils.isEmpty(mailVO.getTime()))
            {//发送时间
                mailVO.setTime(new Date());
                messageHelper.setSentDate(mailVO.getTime());
            }
            mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
            mailVO.setStatus("ok");

        } catch (MessagingException e)
        {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("邮件发送失败");
        }

        return ServerResponse.createBySuccessMessage("邮件发送成功");
    }
}
