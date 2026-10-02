package org.example.common;

/**
 * 统一响应体：所有接口都返回 {code, message, data} 三段式结构
 * code/message 一律取自 {@link ErrorCode}，禁止散落魔法数字
 */
public record Result<T>(int code, String message, T data) {

    /** 成功（带数据） */
    public static <T> Result<T> ok(T data) {
        return new Result<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }

    /** 成功（无数据） */
    public static Result<Void> ok() {
        return new Result<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
    }

    /** 失败：文案用枚举里的默认 message */
    public static Result<Void> error(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /** 失败：追加明细（如参数校验的具体字段错误） */
    public static Result<Void> error(ErrorCode errorCode, String detail) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage() + "：" + detail, null);
    }
}
