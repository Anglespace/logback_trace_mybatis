package com.adire.springdemo1.mapper;

import com.adire.springdemo1.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Adire
 * @create 2024-01-15 17:48
 */
@Mapper
public interface SysUserMapper {

    SysUser getSysUserByUsername(String username);
}
