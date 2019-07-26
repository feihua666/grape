package grape.common.exception;

import lombok.Getter;

/**
 * Created by yangwei
 * Created at 2019/7/26 20:22
 */
@Getter
public enum ExceptionCode {

    ok("0","","成功"),
    fail("-1","","失败"),
    notLogin("-2","","用户未登录"),
    notExist("-404","","数据不存在"),
    error("-500","","系统异常");

    // 编码
    private String code;
    // 二级编码
    private String internalCode;
    // 原因
    private String msg;
    ExceptionCode(String code,String internalCode, String msg) {
        this.code = code;
        this.internalCode = internalCode;
        this.msg = msg;
    }
}
