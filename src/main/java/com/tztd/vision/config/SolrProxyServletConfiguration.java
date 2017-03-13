package com.tztd.vision.config;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SolrProxyServletConfiguration implements EnvironmentAware {

    private Environment environment;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        System.out.println(environment.getProperty("proxy.target.pattern"));
        System.out.println(environment.getProperty("proxy.target.uri"));
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ProxyServlet(), environment.getProperty("proxy.target.pattern"));
        servletRegistrationBean.addInitParameter("targetUri", environment.getProperty("proxy.target.uri"));
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, environment.getProperty("logging_enabled", "false"));
        return servletRegistrationBean;
    }

    private RelaxedPropertyResolver propertyResolver;


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}