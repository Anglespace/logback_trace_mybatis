package com.adire.springdemo1.mapper;

import com.adire.springdemo1.bean.SysUser;

/**
 * @author Adire
 * @create 2024-01-15 17:48
 */
public interface SysUserMapper {

    SysUser getSysUserByUsername(String username);
}
