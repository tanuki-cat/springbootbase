package com.lichi.springbootbase.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lichi.springbootbase.auth.entity.RoleInfo;
import com.lichi.springbootbase.auth.mapper.RoleInfoMapper;
import com.lichi.springbootbase.auth.service.RoleInfoService;
import org.springframework.stereotype.Service;

/**
 * @Description: 角色service接口实现
 * @author: lychee
 * @Date: 2022/12/19 17:25
 * @Version: 1.0
 * @Since: 2022/12/19
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements RoleInfoService {

}
