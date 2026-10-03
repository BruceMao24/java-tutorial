package org.example.common;

/**
 * 错误码枚举：接口返回的 code/message 统一从这里取，禁止在业务代码里散落魔法数字
 *
 * <p>编号分段规则（新增错误码按段递增，段间留空）：
 * <ul>
 *   <li>0      —— 成功</li>
 *   <li>1xxxx  —— 通用错误（参数、请求、系统）</li>
 *   <li>2xxxx  —— 图书业务错误（后续新业务用 3xxxx、4xxxx 递增）</li>
 * </ul>
 */
public enum ErrorCode {

    /** 成功 */
    SUCCESS(0, "success"),

    // ===== 通用错误 1xxxx =====
    /** 参数校验失败（@Validated 触发），message 后追加具体字段错误 */
    PARAM_INVALID(10001, "参数校验失败"),
    /** 服务器内部错误，对外不暴露细节，详情只进日志 */
    INTERNAL_ERROR(19999, "服务器内部错误"),

    // ===== 图书业务 2xxxx =====
    /** 图书不存在（更新/删除的目标 id 查不到） */
    BOOK_NOT_FOUND(20001, "图书不存在");

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
