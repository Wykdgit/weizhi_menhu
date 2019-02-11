package com.wizz.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启基于方法的声明权限控制
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**","/html/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/user/**").hasRole("SUPER")//hasAnyRole("SUPER","ADMIN")
                    .antMatchers("/history2/manage/**","/manage/**","/introduce/manage/**","/system/manage/**","/swiper/manage/**").hasAnyRole("SUPER","ADMIN")
                    .and()
                .formLogin()
                    .loginPage("/welcome/login")//指定login的界面
                    .loginProcessingUrl("/welcome/login")
                    .failureUrl("/welcome/wrong")//指定登录失败的界面
                    .permitAll().successForwardUrl("/welcome/jump")
                    .and()
                .logout()
                    .logoutUrl("/welcome/logout")//指定logout的界面
                    .logoutSuccessUrl("/welcome/login")
                    .invalidateHttpSession(true)
                    .permitAll();

    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        //自定义读取用户信息和角色

        auth
                .inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("super").password(new BCryptPasswordEncoder().encode("root")).roles("SUPER");


        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select name as principal, password as credentials, true from user where name = ?")
                .authoritiesByUsernameQuery("select name as principal, power as role from user where name = ?") ;

    }


}
