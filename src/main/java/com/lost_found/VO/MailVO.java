package com.lost_found.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;

public class MailVO
{
    private String id;//邮件id
    private String author;//邮件发送者名字
    private String from;//邮件发送者邮箱
    private String to;//邮件接收者邮箱
    private String subject;//主题
    private String text;//内容
    private Date time;//发送时间
    private String cc;//抄送
    private String bcc;//密送
    private String status;//状态
    private String error;//报错信息
    private String mobile;//电话号码

    @JsonIgnore
    private MultipartFile[] multipartFiles;//邮件附件
    public MailVO()
    {
    }

    public MailVO(String id, String author, String from, String to, String subject, String text, Date time, String cc
            , String bcc, String status, String error, String mobile, MultipartFile[] multipartFiles)
    {
        this.id = id;
        this.author = author;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.time = time;
        this.cc = cc;
        this.bcc = bcc;
        this.status = status;
        this.error = error;
        this.mobile = mobile;
        this.multipartFiles = multipartFiles;
    }
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getFrom()
    {
        return from;
    }
    public void setFrom(String from)
    {
        this.from = from;
    }
    public String getTo()
    {
        return to;
    }
    public void setTo(String to)
    {
        this.to = to;
    }
    public String getSubject()
    {
        return subject;
    }
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    public String getText()
    {
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public Date getTime()
    {
        return time;
    }
    public void setTime(Date time)
    {
        this.time = time;
    }
    public String getCc()
    {
        return cc;
    }
    public void setCc(String cc)
    {
        this.cc = cc;
    }
    public String getBcc()
    {
        return bcc;
    }
    public void setBcc(String bcc)
    {
        this.bcc = bcc;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getError()
    {
        return error;
    }
    public void setError(String error)
    {
        this.error = error;
    }
    public MultipartFile[] getMultipartFiles()
    {
        return multipartFiles;
    }
    public void setMultipartFiles(MultipartFile[] multipartFiles)
    {
        this.multipartFiles = multipartFiles;
    }
    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    @Override
    public String toString()
    {
        return "MailVO{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", cc='" + cc + '\'' +
                ", bcc='" + bcc + '\'' +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", mobile='" + mobile + '\'' +
                ", multipartFiles=" + Arrays.toString(multipartFiles) +
                '}';
    }
}
