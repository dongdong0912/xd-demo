package com.example.xddemo.demo;

import lombok.Data;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties implements ApplicationContextAware {


    private transient ApplicationContext applicationContext;

    private String name;
    private Father father;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @PostConstruct
    public void init() {

        System.out.println(this.getFather().getName());

        Map<String, Father> providerConfigMap = applicationContext == null ? null : BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, Father.class, false, false);

        System.out.println(providerConfigMap);


        //Father bean = SpringUtil.getBean(Father.class);
        //System.out.println(bean.toString());

        // Server server1 = SpringUtil.getBean("server");

       // System.out.println(server1.toString());

    }






}

