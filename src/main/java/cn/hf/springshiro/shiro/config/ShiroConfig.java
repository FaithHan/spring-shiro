package cn.hf.springshiro.shiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.List;

/**
 * @author : FaithHan
 * @since : 2018/6/8
 */
@Configuration
public class ShiroConfig {

    /**
     * shiro Filter注册
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroSecurityFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthor");
        return shiroFilterFactoryBean;
    }

    /**
     * 注册shiro过滤器到servlet容器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<Filter> springShiroFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DelegatingFilterProxy());
        registrationBean.addInitParameter("targetFilterLifecycle", "true");
        registrationBean.setName("shiroSecurityFilter");
        registrationBean.setOrder(Integer.MIN_VALUE);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    /**
     * shiro 安全管理器
     *
     * @param realms
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultSecurityManager(List<Realm> realms) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms(realms);
        return securityManager;
    }

    /**
     * shiro方法权限拦截配置
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        return new AuthorizationAttributeSourceAdvisor();
    }
}
