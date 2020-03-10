package com.example.config;

import com.example.security.MyFilterSecurityInterceptor;
import com.example.security.myhandler.*;
import com.example.security.userdetails.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;

import javax.annotation.Resource;

/**
 * @author zhaolei
 * Create: 2019/11/19 10:53
 * Modified By:
 * Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyUserDetailService myUserDetailService;

    @Resource
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Value("${auth.skip.antMatchers}")
    private String[] auth_skip_antMatchers;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 用户登录以后security 会把生成的session 放到 SessionRegistry 里面。那么我门想让session 失效只用找到对应的session，然后将它从SessionRegistry 中剔除出去就好了。
     * 但是查看了源码发现SessionRegistry 是 final 。。。。。这个可怎么办呢？
     * 自己new 一个对象吧，所以我就new SessionRegistry(),并用我自己的SessionRegistry管理session 咯
     */
    @Bean
    public SessionRegistry getSessionRegistry(){
        return new SessionRegistryImpl();
    }

    /**
     * 解决 无法直接注入 AuthenticationManager (springboot2.x版本是不可以直接注入)
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 基于数据库中的用户权限信息 进行认证,指定密码加密所使用的加密器为 bCryptPasswordEncoder() 需要将密码加密后写入数据库
        // myUserDetailService类中获取了用户的用户名、密码以及是否启用的信息，查询用户所授予的权限，用来进行鉴权，查询用户作为群组成员所授予的权限
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
        // 不删除凭据，以便记住用户
        auth.eraseCredentials(false);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.addFilterAt(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        http.authorizeRequests()
                // 设置允许匿名的url请求
                .antMatchers(auth_skip_antMatchers).permitAll()
                // 其他地址的访问均需验证权限（需要登录）
                .anyRequest().authenticated()
             .and().exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint) // 未登录处理
                .accessDeniedHandler(myAccessDeniedHandler) // 无权限处理
             .and().formLogin()
                .loginPage("/login") // 设置登陆页面
                .successHandler(myAuthenticationSuccessHandler) // 登陆成功处理
                .failureHandler(myAuthenticationFailureHandler).permitAll() // 登录失败处理
             .and().logout()
                .logoutSuccessUrl("/login").permitAll()
             .and().sessionManagement()
                .invalidSessionUrl("/sessionInvalid"); //session失效处理
                    /*.maximumSessions(1)
                        .maxSessionsPreventsLogin(true) // Session达到最大有效数的时候，不再允许相同的账户登录。
                        .sessionRegistry(sessionRegistry); // 添加 session 注册*/
        // 关闭csrf
        http.csrf().disable();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // 必须要配置一个 WebInvocationPrivilegeEvaluator
        // 的实例，使用自定义的权限拦截器，否则sec:authorize不起作用
        web.privilegeEvaluator(customWebInvocationPrivilegeEvaluator());
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().and().ignoring().antMatchers("/css/**", "/js/**","/image/**");
    }

    private DefaultWebInvocationPrivilegeEvaluator customWebInvocationPrivilegeEvaluator() {
        return new DefaultWebInvocationPrivilegeEvaluator(myFilterSecurityInterceptor);
    }
}
