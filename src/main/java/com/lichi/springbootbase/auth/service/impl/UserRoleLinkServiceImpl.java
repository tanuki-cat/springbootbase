package com.lichi.springbootbase.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lichi.springbootbase.auth.entity.UserRoleLink;
import com.lichi.springbootbase.auth.mapper.UserRoleLinkMapper;
import com.lichi.springbootbase.auth.service.UserRoleLinkService;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户角色关联service接口实现
 * @author: lychee
 * @Date: 2022/12/19 22:32
 * @Version: 1.0
 * @Since: 2022/12/19
 */
@Service
public class UserRoleLinkServiceImpl extends ServiceImpl<UserRoleLinkMapper, UserRoleLink> implements UserRoleLinkService {

}
