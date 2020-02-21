package com.lost_found.service.Impl;

import com.lost_found.common.ServerResponse;
import com.lost_found.dao.ItemTypeMapper;
import com.lost_found.pojo.ItemType;
import com.lost_found.pojo.Post;
import com.lost_found.service.IItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ItemTypeService implements IItemTypeService {
    @Autowired
    ItemTypeMapper itemtypeMapper;


    @Override
    public ServerResponse<ItemType> getType(Integer id) {

        ItemType type = itemtypeMapper.selectByPrimaryKey(id);
        if(type!=null){
            return ServerResponse.createBySuccess(type);

        }
        return ServerResponse.createByErrorMessage("未找到物品类型信息");

    }

    @Override
    public ServerResponse<List<ItemType>> getAllType() {
        return ServerResponse.createBySuccess(itemtypeMapper.getAllType());

    }

    @Override
    public ServerResponse<String> addType(ItemType type) {
        type.setCreateTime(new Date());
        type.setUpdateTime(new Date());
        return itemtypeMapper.insert(type) > 0 ?
                ServerResponse.createBySuccessMessage("添加成功") :
                ServerResponse.createByErrorMessage("添加失败,请稍后再试");
    }

    @Override
    public ServerResponse<String> removeType(Integer id) {
        return itemtypeMapper.deleteByPrimaryKey(id)>0?
                ServerResponse.createBySuccessMessage("删除成功"):
        ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> updateType(ItemType type) {
        type.setUpdateTime(new Date());
        return itemtypeMapper.updateByPrimaryKeySelective(type)>0?
                ServerResponse.createBySuccessMessage("修改成功"):
                ServerResponse.createByErrorMessage("修改失败");

    }
}
