package com.lost_found.service;

import com.lost_found.VO.MailVO;
import com.lost_found.common.ServerResponse;

public interface MailService
{
    ServerResponse sendMail(MailVO mailVO);

    ServerResponse checkMail(MailVO mailVO);

    ServerResponse sendMimeMail(MailVO mailVO);
}
