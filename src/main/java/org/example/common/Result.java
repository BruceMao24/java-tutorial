package org.example.common;

/**
 * 统一响应体：所有接口都返回 {code, message, data} 三段式结构
 * code = 0 表示成功，非 0 为业务错误码
 */
public record Result<T>(int code, String message, T data) {

    /** 成功（带数据） */
    public static <T> Result<T> ok(T data) {
        return new Result<>(0, "success", data);
    }

    /** 成功（无数据） */
    public static Result<Void> ok() {
        return new Result<>(0, "success", null);
    }

    /** 失败 */
    public static Result<Void> error(int code, String message) {
        return new Result<>(code, message, null);
    }
}
