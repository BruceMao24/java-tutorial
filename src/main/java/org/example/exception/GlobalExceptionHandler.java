package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理器：集中处理所有 Controller 抛出的异常，
 * 把难看的框架默认错误页换成统一的 JSON 响应
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数校验失败（@Validated 触发的 MethodArgumentNotValidException）
     * 返回 400 + 具体的字段错误信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidation(MethodArgumentNotValidException e) {
        String detail = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return Map.of("code", 400, "message", "参数校验失败：" + detail);
    }

    /**
     * 兜底：其他所有未处理的异常，返回 500，避免把堆栈直接暴露给调用方
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception e) {
        return Map.of("code", 500, "message", "服务器内部错误：" + e.getMessage());
    }
}
