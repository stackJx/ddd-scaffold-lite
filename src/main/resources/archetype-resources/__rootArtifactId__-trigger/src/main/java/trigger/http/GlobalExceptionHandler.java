#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.trigger.http;

import ${package}.api.response.Response;
import ${package}.types.enums.ResponseCode;
import ${package}.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public Response<?> handleAppException(AppException e, HttpServletRequest request) {
        log.error("业务异常 [{}] {}", request.getRequestURI(), e.getInfo(), e);
        return Response.fail(e.getCode(), e.getInfo());
    }

    @ExceptionHandler(BindException.class)
    public Response<?> handleBindException(BindException e) {
        String message = e.getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.warn("参数校验失败: {}", message);
        return Response.fail(ResponseCode.ILLEGAL_PARAMETER.getCode(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.warn("参数校验失败: {}", message);
        return Response.fail(ResponseCode.ILLEGAL_PARAMETER.getCode(), message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Response<?> handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        log.warn("参数校验失败: {}", message);
        return Response.fail(ResponseCode.ILLEGAL_PARAMETER.getCode(), message);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.warn("请求方式不支持 [{}] {}", request.getMethod(), request.getRequestURI());
        return Response.fail(ResponseCode.METHOD_NOT_ALLOWED.getCode(), "请求方式 " + e.getMethod() + " 不支持");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Response<?> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.warn("资源不存在: {} {}", e.getHttpMethod(), e.getRequestURL());
        return Response.fail(ResponseCode.NOT_FOUND.getCode(), "资源不存在");
    }

    @ExceptionHandler(Exception.class)
    public Response<?> handleException(Exception e, HttpServletRequest request) {
        log.error("系统异常 [{}]", request.getRequestURI(), e);
        return Response.fail(ResponseCode.SYSTEM_ERROR.getCode(), "系统异常，请稍后重试");
    }

}
