package com.lost_found.service.Impl;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
import com.lost_found.service.IPostService;

public class PostService implements IPostService {


    @Override
    public ServerResponse announceInfo(Post info){
        return ServerResponse.createBySuccessMessage("nidadaw");
    }

}
