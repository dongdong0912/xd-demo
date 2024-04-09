/*
 * Copyright (c) 2001-2024 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.xddemo.demo;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author helloztt
 * @version V1.0
 * @since 2024-03-28 10:43
 */
public class ValidatorUtils {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 手动校验
     *
     * @param object
     * @param groups
     * @return 返回校验失败的错误信息
     */
    public static List<String> validateEntity(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        List<String> errorMsgList = new ArrayList<>();
        if (!constraintViolations.isEmpty()) {
            StringBuilder errorMsg = new StringBuilder();
            for (ConstraintViolation<?> e : constraintViolations) {
                errorMsgList.add(e.getMessage());
            }
        }
        return errorMsgList;
    }
}
