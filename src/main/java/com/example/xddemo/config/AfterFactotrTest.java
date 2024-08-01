package com.example.xddemo.config;

import com.example.xddemo.demo.Father;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Author: xuedong
 * Date: 2024/8/1
 */
@Component
public class AfterFactotrTest implements FactoryBean<Father>, ApplicationContextAware, InitializingBean {
    @Override
    public Father getObject() throws Exception {
        System.out.println("FactoryBean调用");
        return new Father();
    }

    @Override
    public Class<Father> getObjectType() {
        return Father.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean调用");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("ApplicationContext调用");

    }
}
