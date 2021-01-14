package com.onlinetest.online.handler;


import com.onlinetest.online.dto.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**统一异常处理方法
 * @author kevinhuang
 * @date 2020-01-02 19:20
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private Result exceptionHandlder(HttpServletRequest req, Exception e){
       return Result.buildError(e.getMessage(),-1);
    }

}
