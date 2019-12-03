package com.lost_found.controller;

import com.lost_found.common.ServerResponse;
import com.lost_found.pojo.ItemType;
import com.lost_found.service.Impl.ItemTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags="物品类型")
@RestController
@RequestMapping("/itemType")
public class ItemTypeController
{
    @Autowired
    ItemTypeService itemTypeService;


    /**
     * 根据post对象添加发布信息
     * @param type
     * @return
     */
    @ApiOperation(value = "添加物品类型")
    @PostMapping
    public ServerResponse addType(@RequestBody ItemType type)
    {
        return itemTypeService.addType(type);
    }

    /**
 * 根据id删除物品类型
 *
 * @param id
 * @return
 */
    @ApiOperation(value = "删除物品类型")
    @ApiImplicitParam(name="id",value="类型Id",required=true,paramType="path",dataType = "int",example = "1")
    @DeleteMapping("/{id:\\d+}")
    public ServerResponse<String> removeType(@PathVariable Integer id)
    {
        return itemTypeService.removeType(id);
    }

    /**
     * 更新物品类型的数据
     *
     * @param type
     * @return
     */
    @ApiOperation(value = "修改物品类型")
    @PutMapping
    public ServerResponse<String> updatePost(@RequestBody ItemType type)
    {
        return itemTypeService.updateType(type);
    }

    /**
     * 根据帖子的Id获得帖子
     * @return
     */
    @ApiOperation(value = "获得单个物品类型")
    @ApiImplicitParam(name="id",value="物品类型Id",required=true,paramType="query",dataType = "int",example = "1")
    @GetMapping
    public ServerResponse<ItemType> getPost(Integer id)
    {
        return itemTypeService.getType(id);
    }

/**
 * 获取所有的物品类型
 *
 */

    @ApiOperation(value = "获得所有的类型")
    @GetMapping("/getAllType")
    public ServerResponse<List<ItemType>> getAllType(){
        return itemTypeService.getAllType();
    }

}
