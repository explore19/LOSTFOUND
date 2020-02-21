package com.lost_found.form;

import com.lost_found.common.BasePagination;

/**
 * @description:
 * @author: ashe
 * @DATE: 2020/2/17 16:53
 */
public class QueryUserForm extends BasePagination {
    String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
