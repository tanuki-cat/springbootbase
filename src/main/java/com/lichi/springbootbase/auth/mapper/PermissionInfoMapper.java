package com.lichi.springbootbase.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lichi.springbootbase.auth.entity.PermissionInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 权限dao层接口
 * @author: lychee
 * @date: 2022/12/19 16:57
 * @version: 1.0
 * @since: 2022/12/19
 */
@Mapper
public interface PermissionInfoMapper extends BaseMapper<PermissionInfo> {

}