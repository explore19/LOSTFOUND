package com.lost_found.VO;

import com.lost_found.pojo.Reply;

import java.util.LinkedList;
import java.util.List;

public class ReplyTree
{
    private ReplyVO reply;
    private List<ReplyTree> children;

    public ReplyTree()
    {
        this.reply = new ReplyVO();
        this.children = new LinkedList<>();
    }

    public ReplyTree(ReplyVO reply)
    {
        this.reply = reply;
        this.children = new LinkedList<>();
    }

    public ReplyVO getReply()
    {
        return reply;
    }
    public void setReply(ReplyVO reply)
    {
        this.reply = reply;
    }
    public List<ReplyTree> getChildren()
    {
        return children;
    }
    public void setChildren(List<ReplyTree> children)
    {
        this.children = children;
    }

    public ReplyTree add(ReplyTree parent, ReplyVO reply)
    {
        ReplyTree child = new ReplyTree(reply);
        parent.children.add(child);
        return child;
    }

    public ReplyTree findChild(ReplyTree parent, ReplyVO reply)
    {
        Integer id = reply.getReply().getId();
        for (ReplyTree child : parent.getChildren())
        {
            if (child.getReply().getReply().getId() == id)
            {
                return child;
            }
        }
        return null;
    }
}
