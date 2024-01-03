package com.example.xddemo.scheduled;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * Author: xuedong
 * Date: 2024/1/3
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class DistributedSchedulingConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DistributedScheduledAnnotationBeanPostProcessor distributedScheduledAnnotationBeanPostProcessor() {
        return new DistributedScheduledAnnotationBeanPostProcessor();
    }
}
