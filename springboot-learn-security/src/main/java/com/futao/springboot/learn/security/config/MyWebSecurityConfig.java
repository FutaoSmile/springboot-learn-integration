package com.futao.springboot.learn.security.config;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author futao
 * Created on 2019-07-08.
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 密码加密方式
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //密码无加密
//        return NoOpPasswordEncoder.getInstance();
        //秘钥迭代次数
        return new BCryptPasswordEncoder(10);
    }

    /**
     * 基于内存的配置 用户名密码角色
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("$2a$10$ltemjyGkgpfRQaB3uI/4AuFv6wEbKpTyQ4KB4x0FP5vGgNz2E8/4G").roles("admin", "user")
                .and()
                .withUser("DBA").password("DBA").roles("admin", "DBA")
                .and()
                .withUser("normal").password("123456").roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").access("hasAnyRole('admin','user')")
                .antMatchers("/db/**").access("hasRole('admin') and hasRole('DBA')")
                //除了配置之外的地址都需要认证了才能访问
                .anyRequest()
                //除了配置之外的地址都需要认证了才能访问
                .authenticated()
                .and()


                //开启表单登录.登录接口为/login.登录参数的用户名必须是username,密码必须是password
                .formLogin()
                .loginPage("/login_page")
                //配置该接口的目的是方便ajax直接通过接口登录
                .loginProcessingUrl("/api/login")
                .usernameParameter("userName")
                .passwordParameter("pwd")
                //登录验证成功
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    Object principal = authentication.getPrincipal();
                    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    PrintWriter writer = httpServletResponse.getWriter();
                    httpServletResponse.setStatus(HttpStatus.OK.value());
                    //可以根据自己项目的数据结构来定义
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("status", HttpStatus.OK.value());
                    map.put("msg", "当前用户信息为：" + JSON.toJSONString(principal));
                    writer.write(JSON.toJSONString(map));
                    writer.flush();
                    writer.close();
                })
                //登录验证失败
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    PrintWriter writer = httpServletResponse.getWriter();
                    httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                    //可以根据自己项目的数据结构来定义
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("status", HttpStatus.UNAUTHORIZED.value());
                    map.put("msg", "登录失败" + e.getMessage());
                    if (e instanceof LockedException) {
                        map.put("msg", "用户被锁定，登录失败" + e.getMessage());
                    } else if (e instanceof BadCredentialsException) {
                        map.put("msg", "用户名或密码错误，登录失败" + e.getMessage());
                    } else if (e instanceof DisabledException) {
                        map.put("msg", "账号被禁用，登录失败" + e.getMessage());
                    } else if (e instanceof AccountExpiredException) {
                        map.put("msg", "账户已过期，登录失败" + e.getMessage());
                    } else if (e instanceof CredentialsExpiredException) {
                        map.put("msg", "密码已过期，登录失败" + e.getMessage());
                    }
                    writer.write(JSON.toJSONString(map));
                    writer.flush();
                    writer.close();
                })
                //和登录相关的接口都需要认证即可访问
                .permitAll()
                .and()


                //注销处理
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    //请求注销->数据清理工作

                })
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    //注销成功之后的逻辑，如重定向到首页等

                })
                .and()
                //关闭csrf（跨域请求伪造)
                .csrf()
                .disable();
    }
}
