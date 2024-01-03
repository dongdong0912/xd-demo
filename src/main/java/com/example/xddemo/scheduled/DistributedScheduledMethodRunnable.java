package com.example.xddemo.scheduled;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Objects;

/**
 * Author: xuedong
 * Date: 2024/1/3
 */
public class DistributedScheduledMethodRunnable implements Runnable {

    private final Object target;

    private final Method method;


    /**
     * Create a {@code ScheduledMethodRunnable} for the given target instance,
     * calling the specified method.
     *
     * @param target the target instance to call the method on
     * @param method the target method to call
     */
    public DistributedScheduledMethodRunnable(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    /**
     * Create a {@code ScheduledMethodRunnable} for the given target instance,
     * calling the specified method by name.
     *
     * @param target     the target instance to call the method on
     * @param methodName the name of the target method
     * @throws NoSuchMethodException if the specified method does not exist
     */
    public DistributedScheduledMethodRunnable(Object target, String methodName) throws NoSuchMethodException {
        this.target = target;
        this.method = target.getClass().getMethod(methodName);
    }


    /**
     * Return the target instance to call the method on.
     */
    public Object getTarget() {
        return this.target;
    }

    /**
     * Return the target method to call.
     */
    public Method getMethod() {
        return this.method;
    }


    @Override
    public void run() {
        try {
            DistributedScheduled annotation = AnnotationUtils.findAnnotation(method, DistributedScheduled.class);
            if (Objects.isNull(annotation)) {
                throw new RuntimeException("获取方法上的分布式注解失败");
            }
            String name = method.getName();
            boolean b = RedisUtils.tryLock(name, 5);
            if (b) {
                ReflectionUtils.makeAccessible(this.method);
                this.method.invoke(this.target);
            }
        } catch (InvocationTargetException ex) {
            ReflectionUtils.rethrowRuntimeException(ex.getTargetException());
        } catch (IllegalAccessException ex) {
            throw new UndeclaredThrowableException(ex);
        } finally {
            RedisUtils.delete(method.getName());
        }
    }

    @Override
    public String toString() {
        return this.method.getDeclaringClass().getName() + "." + this.method.getName();
    }
}
