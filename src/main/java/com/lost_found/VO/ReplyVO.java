package com.lost_found.VO;

import com.lost_found.pojo.Reply;


public class ReplyVO
{
    private Reply reply;
    private String headPortrait;
    private String nickName;
    private Integer status;

    public Reply getReply()
    {
        return reply;
    }
    public void setReply(Reply reply)
    {
        this.reply = reply;
    }
    public String getHeadPortrait()
    {
        return headPortrait;
    }
    public void setHeadPortrait(String headPortrait)
    {
        this.headPortrait = headPortrait;
    }
    public String getNickName()
    {
        return nickName;
    }
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    public Integer getStatus()
    {
        return status;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    public ReplyVO()
    {
    }

    public ReplyVO(Reply reply, String headPortrait, String nickName, Integer status)
    {
        this.reply = reply;
        this.headPortrait = headPortrait;
        this.nickName = nickName;
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ReplyVO{" +
                "reply=" + reply +
                ", headPortrait='" + headPortrait + '\'' +
                ", nickName='" + nickName + '\'' +
                ", status=" + status +
                '}';
    }
}
