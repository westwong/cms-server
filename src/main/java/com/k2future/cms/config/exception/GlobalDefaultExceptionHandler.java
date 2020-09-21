package com.k2future.cms.config.exception;


import com.yunque.commons.util.RespBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<Object, Object> handleException(Exception e, HttpServletRequest request) {
        logger.error("unhandled Exception", e);
        logger.info("path {} ", request.getServletPath());
        return RespBuilder.errorJsonStr("A fatal error has occurred");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public Map<Object, Object> handleException(DataIntegrityViolationException e) {
        logger.error("unhandled Exception", e);
        return RespBuilder.errorJsonStr("something happen with mybatis");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Map<Object, Object> IllegalArgumentException(IllegalArgumentException e) {
        logger.error("ill {}", e.getMessage());
        e.printStackTrace();
        return RespBuilder.errorJsonStr(e.getMessage());
    }

    /**
     * secured method ex
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Map<Object, Object> AccessDeniedException(AccessDeniedException e) {
        return RespBuilder.errorJsonStr("Unauthorized", RespBuilder.UNAUTHORIZED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Map<Object, Object> MissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return RespBuilder.errorJsonStr(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<Object, Object> BindException(BindException e) {
        logger.info("BindException", e);
        return RespBuilder.errorJsonStr("wrong request param");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Map<Object, Object> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return RespBuilder.errorJsonStr(e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Map<Object, Object> HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return RespBuilder.errorJsonStr(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Map<Object, Object> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("{}", e.getMessage());
        return RespBuilder.errorJsonStr("need json body or make sure your json is right ");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<Object, Object> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        return RespBuilder.errorJsonStr(fieldError.getField() + " " + fieldError.getRejectedValue() + "?");
    }


    /**
     * we should choose the right path, not just the easy path
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Map<Object, Object> NullPointerException(NullPointerException e) {
        logger.error("NullPointerException ", e);
        return RespBuilder.errorJsonStr("参照文档检查, 必传参数不为空且值有效");
    }


}
