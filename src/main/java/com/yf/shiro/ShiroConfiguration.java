

package com.yf.shiro;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yf.controller.TimerSocket;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import java.util.LinkedHashMap;

/**
 * shiro的配置类
 * @author sujin
 *
 */
@Configuration
public class ShiroConfiguration {
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/index.html");
        bean.setSuccessUrl("/success.html");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/success.html", "authc"); //表示可以匿名访问
        filterChainDefinitionMap.put("/fail.html", "authc");
//        filterChainDefinitionMap.put("/success.html", "perms[select]");
    
/*      filterChainDefinitionMap.put("/index", "authc");//代表着前面的url路径，用后面指定的拦截器进行拦截
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/loginUser", "anon");
        filterChainDefinitionMap.put("/admin", "roles[admin]");//admin的url，要用角色是admin的才可以登录,对应的拦截器是RolesAuthorizationFilter
        filterChainDefinitionMap.put("/edit", "perms[edit]");//拥有edit权限的用户才有资格去访问
        filterChainDefinitionMap.put("/druid/**", "anon");//所有的druid请求，不需要拦截，anon对应的拦截器不会进行拦截
        filterChainDefinitionMap.put("/**", "user");//所有的路径都拦截，被UserFilter拦截，这里会判断用户有没有登陆
*/
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }
    //配置自定义的权限登录器
    @Bean(name="authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm=new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }
    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    //session管理器
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    //开启shiro aop注解支持.使用代理方式;所以需要开启代码支持;
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
    
    @Bean
    public TimerSocket addPrincipalToSessionFilter(){
        return  new TimerSocket();
    }
    
    
    //在thymeleaf中使用shiro的自定义tag
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
    return new ShiroDialect();
    }
}

