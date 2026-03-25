#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS("0000", "成功"),
    UN_ERROR("0001", "未知失败"),
    ILLEGAL_PARAMETER("0002", "非法参数"),
    UNAUTHORIZED("0003", "未授权"),
    FORBIDDEN("0004", "无权限"),
    NOT_FOUND("0005", "资源不存在"),
    METHOD_NOT_ALLOWED("0006", "请求方式不支持"),
    SYSTEM_ERROR("0007", "系统异常"),
    ;

    private final String code;
    private final String info;

}
