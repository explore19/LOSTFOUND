package com.lost_found.VO;


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

    /**
     * 向parent代表的节点下插入一个子节点
     * @param parent
     * @param reply
     * @return
     */
    public ReplyTree add(ReplyTree parent, ReplyVO reply)
    {
        ReplyTree child = new ReplyTree(reply);
        parent.children.add(child);
        return child;
    }

    /**
     * 在parent的子结点中寻找reply是指定的reply的节点
     * @param parent
     * @param reply
     * @return
     */
    public ReplyTree findChild(ReplyTree parent, ReplyVO reply)
    {
        Integer id = reply.getReply().getId();
        for (ReplyTree child : parent.getChildren())
        {
            if (child.getReply().getReply().getId().equals(id))
            {
                return child;
            }
        }
        return null;
    }
}
