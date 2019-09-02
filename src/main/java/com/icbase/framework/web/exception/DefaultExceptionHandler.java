package com.icbase.framework.web.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.icbase.common.exception.DemoModeException;
import com.icbase.framework.web.domain.Message;

/**
 * 自定义异常处理器
 * 
 * @author IC-Base
 */
@RestControllerAdvice
public class DefaultExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);
    
    /**
     * 权限校验失败
     */
    @ExceptionHandler(AuthorizationException.class)
    public Message handleAuthorizationException(AuthorizationException e)
    {
        log.error(e.getMessage(), e);
        return Message.error("您没有数据的权限，请联系管理员添加");
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public Message handleException(HttpRequestMethodNotSupportedException e)
    {
        log.error(e.getMessage(), e);
        return Message.error("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Message notFount(RuntimeException e)
    {
        log.error("运行时异常:", e);
        return Message.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Message handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return Message.error("服务器错误，请联系管理员");
    }
    
    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public Message demoModeException(DemoModeException e)
    {
        return Message.error("演示模式，不允许操作");
    }

}
