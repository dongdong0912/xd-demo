package com.example.xddemo.scheduled;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedSchedules {

    DistributedScheduled[] value();
}
