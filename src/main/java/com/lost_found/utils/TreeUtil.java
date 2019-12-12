package com.lost_found.utils;


import com.lost_found.VO.ReplyTree;
import com.lost_found.VO.ReplyVO;
import com.lost_found.dao.ReplyMapper;
import com.lost_found.dao.UserMapper;
import com.lost_found.pojo.Reply;
import com.lost_found.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class TreeUtil
{
    @Autowired
    UserMapper userMapper;

    @Autowired
    ReplyMapper replyMapper;

    public static TreeUtil treeUtil;

    //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Servlet的inti()方法。
    // 被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
    //应用 PostConstruct 注释的方法必须遵守以下所有标准：该方法不得有任何参数
    //该方法的返回类型必须为 void；该方法不得抛出已检查异常；应用 PostConstruct 的方法可以是 public、protected、package private 或 private；
    // 除了应用程序客户端之外，该方法不能是 static；该方法可以是 final
    @PostConstruct
    public void init()
    {
        treeUtil = this;
    }

    public static ReplyTree getTree(List<Reply> replyList)
    {
        ReplyTree parent = new ReplyTree();
        List<Reply> list = new ArrayList<>(replyList);
        TrasnferToTree(parent, replyList, list);

        return parent;
    }

    /**
     * 将list转化为树
     * @param parent
     * @param replyList
     * @param list
     */
    public static void TrasnferToTree(ReplyTree parent, List<Reply> replyList, List<Reply> list)
    {
        if (list.isEmpty())
            return;

        //因为需要边迭代边删除, 所以使用迭代器
        Iterator<Reply> iterator = replyList.iterator();
        while (iterator.hasNext())
        {
            Reply reply = iterator.next();
            User user = treeUtil.userMapper.selectByPrimaryKey(reply.getUserId());
            String replyedUserNickName = null;
            if (reply.getType() == 1)
            {
                User repledUser = treeUtil.userMapper.selectByPrimaryKey(treeUtil.replyMapper.selectByPrimaryKey(reply.getReplyId()).getUserId());
                replyedUserNickName = repledUser.getNickName();
            }
            ReplyVO replyVO = new ReplyVO(reply, user.getHeadPortrait(), user.getNickName(), reply.getType(), replyedUserNickName);

            //是回复帖子的回复, 而且现在的父节点为null, 则说明应该插入该节点
            if (reply.getType() == 0 && parent.getReply().getReply() == null)
            {
                parent.add(parent, replyVO);
                list.remove(reply);
                if (isLeaf(replyVO, replyList))
                    continue;
                TrasnferToTree(parent.findChild(parent, replyVO), replyList, list);
            }
            //如果是回复回复的回复且该节点的回复确实是回复父节点的回复, 则插入
            else if (parent.getReply().getReply() != null && reply.getType() == 1 && reply.getReplyId() == parent.getReply().getReply().getId())
            {
                parent.add(parent, replyVO);
                list.remove(reply);
                TrasnferToTree(parent.findChild(parent, replyVO), replyList, list);
            }
        }
    }

    /**
     * 判断是否是叶子节点
     * @param replyVO
     * @param replyList
     * @return
     */
    public static boolean isLeaf(ReplyVO replyVO, List<Reply> replyList)
    {
        boolean flag = true;
        for (Reply reply : replyList)
        {
            if (reply.getReplyId() == replyVO.getReply().getId())
                flag = false;
        }
        return flag;
    }
}
