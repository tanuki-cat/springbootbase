package com.lichi.springbootbase.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lichi.springbootbase.auth.entity.PermissionInfo;
import com.lichi.springbootbase.auth.mapper.PermissionInfoMapper;
import com.lichi.springbootbase.auth.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @Description: 权限service接口实现
 * @author: lychee
 * @Date: 2022/12/19 17:23
 * @Version: 1.0
 * @Since: 2022/12/19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionInfoMapper, PermissionInfo> implements PermissionService {

}
