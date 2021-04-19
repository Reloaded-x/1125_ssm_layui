package com.sy.handler;

import com.sy.model.Result;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @ClassName ErrorHandler
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/4/13 10:45
 * @Version 1.0
 */
@RestControllerAdvice
public class ErrorHandler {

    private final static Logger LOGGER = Logger.getLogger(ErrorHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Result handleError(Exception ex){
        Result result = new Result();
        result.respError();
        //日志
        LOGGER.error(ex.getMessage(), ex);
        return result;
    }
}
