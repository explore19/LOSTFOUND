package com.lost_found.service.Impl;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.Post;
import com.lost_found.service.IPostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService implements IPostService {


    @Override
    public ServerResponse add(Post post){
        return ServerResponse.createBySuccessMessage("nidadaw");
    }

}
