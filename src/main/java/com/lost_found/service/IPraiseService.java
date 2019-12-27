package com.lost_found.service;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Praise;

public interface IPraiseService
{
    ServerResponse praise(Integer postId);

    boolean checkPraise(Integer postId);
}
