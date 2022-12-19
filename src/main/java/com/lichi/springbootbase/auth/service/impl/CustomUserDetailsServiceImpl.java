package com.lichi.springbootbase.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lichi.springbootbase.auth.entity.RoleInfo;
import com.lichi.springbootbase.auth.entity.UserDetail;
import com.lichi.springbootbase.auth.entity.UserInfo;
import com.lichi.springbootbase.auth.entity.UserRoleLink;
import com.lichi.springbootbase.auth.service.RoleInfoService;
import com.lichi.springbootbase.auth.service.UserInfoService;
import com.lichi.springbootbase.auth.service.UserRoleLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 自定义UserDetailService实现
 * @author: lychee
 * @Date: 2022/12/19 21:15
 * @Version: 1.0
 * @Since: 2022/12/19
 */
@Service("customUserDetailService")
@Slf4j
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private UserRoleLinkService userRoleLinkService;


    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("开始验证登录用户,username：{}",username);
        LambdaQueryWrapper<UserInfo> query = new LambdaQueryWrapper<>();
        query.eq(UserInfo::getUsername,username);
        UserInfo userInfo = userInfoService.getOne(query);
        if (ObjectUtils.isNotEmpty(userInfo)) {
            throw new UsernameNotFoundException("用户名或密码错误,登录失败");
        }

        //原则上单sql 只查询单表，尽量避免连表查询
        //构建信息
        UserDetail userDetail = new UserDetail();
        userDetail.setUserInfo(userInfo);
        //获取用户角色关联信息
        List<UserRoleLink> roleLinkInfos = userRoleLinkService.list(Wrappers.<UserRoleLink>query()
                .lambda()
                .eq(UserRoleLink::getUserId, userInfo.getId()));
        //从关联信息中获取角色id
        List<Long> roleIds = roleLinkInfos.stream().map(UserRoleLink::getRoleId).toList();
        //获取角色信息
        List<RoleInfo> roleInfos = roleInfoService.list(Wrappers.<RoleInfo>query()
                .lambda()
                .in(RoleInfo::getId, roleIds));
        userDetail.setRoleInfoList(roleInfos);
        return userDetail;
    }
}
