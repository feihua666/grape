package grape.common.exception;

import lombok.Getter;

/**
 * 异常编码定义，如果是http返回的异常，建议配合使用httpStatus状态来表示
 * Created by yangwei
 * Created at 2019/7/26 20:22
 */
@Getter
public enum ExceptionCode {

    ok("0",  "成功"),
    fail("-1",  "失败"),
    notLogin("-2",  "用户未登录，登录后才能操作"),
    notExist("-404",  "数据不存在"),
    unAuthorized("-403",  "没有授权"),
    error("-500", "系统异常");

    // 编码
    private String code;
    // 原因
    private String msg;

    ExceptionCode(String code,  String msg) {
        this.code = code;
        this.msg = msg;
    }
}
