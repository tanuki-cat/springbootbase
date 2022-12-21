package com.lichi.springbootbase.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lichi.springbootbase.auth.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 用户信息dao层接口
 * @author: lychee
 * @date: 2022/12/19 17:01
 * @version: 1.0
 * @since: 2022/12/19
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
