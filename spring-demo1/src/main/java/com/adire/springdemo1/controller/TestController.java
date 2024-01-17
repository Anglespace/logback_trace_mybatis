package com.adire.springdemo1.controller;

import com.adire.springdemo1.bean.SysUser;
import com.adire.springdemo1.mapper.SysUserMapper;
import com.adire.springdemo1.util.MDCTraceUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adire
 * @create 2024-01-15 10:46
 */
@RequestMapping("/test")
@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @GetMapping("/hello")
    public String helloWorld(HttpServletRequest request, HttpServletResponse response){
        log.info("Hello World!");
        SysUser adire = sysUserMapper.getSysUserByUsername("Adire");
        return "Hello World!";
    }
}
