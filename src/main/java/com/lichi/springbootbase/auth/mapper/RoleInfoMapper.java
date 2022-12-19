package com.lichi.springbootbase.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lichi.springbootbase.auth.entity.RoleInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 用户角色信息dao层接口
 * @author: lychee
 * @Date: 2022/12/19 17:06
 * @Version: 1.0
 * @Since: 2022/12/19
 */
@Mapper
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {
}
