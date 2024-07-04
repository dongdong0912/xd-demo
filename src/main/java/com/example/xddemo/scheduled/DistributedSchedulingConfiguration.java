package com.example.xddemo.scheduled;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

/**
 * Author: xuedong
 * Date: 2024/1/3
 */
public class DistributedSchedulingConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DistributedScheduledAnnotationBeanPostProcessor distributedScheduledAnnotationBeanPostProcessor() {
        return new DistributedScheduledAnnotationBeanPostProcessor();
    }
}
