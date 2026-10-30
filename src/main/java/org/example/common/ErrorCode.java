package org.example.common;

/**
 * 业务错误码：统一定义 code 与默认文案，禁止散落魔法数字
 * code 分段约定：0=成功，1xxxx=参数类错误，2xxxx=业务类错误，5xxxx=系统类错误
 */
public enum ErrorCode {

    /** 成功 */
    SUCCESS(0, "成功"),

    /** 参数校验失败 */
    PARAM_INVALID(10001, "参数校验失败"),

    /** 图书不存在 */
    BOOK_NOT_FOUND(20001, "图书不存在"),

    /** 请求的资源/接口不存在 */
    NOT_FOUND(20002, "资源不存在"),

    /** 用户名已存在 */
    USERNAME_EXISTS(20003, "用户名已存在"),

    /** 用户名或密码错误 */
    LOGIN_FAILED(20004, "用户名或密码错误"),

    /** 两次密码不一致 */
    PASSWORD_MISMATCH(20005, "两次密码不一致"),

    /** 未登录或 token 无效 */
    UNAUTHORIZED(20006, "未登录或登录已过期"),

    /** 旧密码错误 */
    PASSWORD_INCORRECT(20007, "旧密码错误"),

    /** 系统内部错误 */
    INTERNAL_ERROR(50001, "系统内部错误");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
