package com.lost_found.controller;

import com.lost_found.VO.MailVO;
import com.lost_found.common.ServerResponse;
import com.lost_found.service.Impl.MailServiceImpl;
import com.lost_found.service.MailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "意见反馈")
@RestController
@RequestMapping("/feedback")
public class FeedBackController
{
    @Autowired
    private MailService mailService;

    @PostMapping("/send_feedback")
    public ServerResponse sendFeedBack(@RequestBody MailVO mailVO)
    {
        System.out.println(mailVO.toString());

        mailVO.setText(mailVO.getText() + "\n\n联系方式: " + mailVO.getMobile() + "\nTa的邮箱: " + mailVO.getFrom());
        mailVO.setTo("explorefeedback@163.com");
        mailVO.setSubject("收到【"+mailVO.getAuthor()+"】的意见反馈");
        mailVO.setFrom("1347006947@qq.com");
        return mailService.sendMail(mailVO);
    }
}
