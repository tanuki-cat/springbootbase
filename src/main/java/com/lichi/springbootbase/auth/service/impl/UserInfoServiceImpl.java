package com.lichi.springbootbase.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lichi.springbootbase.auth.entity.UserInfo;
import com.lichi.springbootbase.auth.mapper.UserInfoMapper;
import com.lichi.springbootbase.auth.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户信息service接口实现
 * @author: lychee
 * @Date: 2022/12/19 17:13
 * @Version: 1.0
 * @Since: 2022/12/19
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
