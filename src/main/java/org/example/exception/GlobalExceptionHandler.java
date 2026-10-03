package org.example.exception;

import java.util.stream.Collectors;
import org.example.common.ErrorCode;
import org.example.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * 全局异常处理器：集中处理所有 Controller 抛出的异常，
 * 把难看的框架默认错误页换成统一的 Result JSON 响应
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 参数校验失败（@Validated 触发的 MethodArgumentNotValidException）
     * 返回 400 + 具体的字段错误信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValidation(MethodArgumentNotValidException e) {
        String detail = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        log.warn("参数校验失败: {}", detail);
        return Result.error(ErrorCode.PARAM_INVALID, detail);
    }

    /**
     * 资源/接口不存在（如浏览器自动请求的 /favicon.ico、拼错的接口路径）
     * 属于正常 404，不该走兜底打 error 日志、更不该返回 500
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
        NoResourceFoundException.class,
        NoHandlerFoundException.class,
    })
    public Result<Void> handleNotFound(Exception e) {
        log.debug("资源不存在: {}", e.getMessage());
        return Result.error(ErrorCode.NOT_FOUND);
    }

    /**
     * 兜底：其他所有未处理的异常，返回 500
     * 注意：对外只给通用文案，e.getMessage() 可能带 SQL/内部细节，只能进日志
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("服务器内部错误", e);
        return Result.error(ErrorCode.INTERNAL_ERROR);
    }
}
