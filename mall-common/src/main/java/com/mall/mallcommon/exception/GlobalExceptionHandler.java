

package com.mall.mallcommon.exception;

import brave.Tracer;
import com.alibaba.fastjson.JSONObject;
import com.mall.mallcommon.vo.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sun.security.validator.ValidatorException;


@Slf4j
@RestControllerAdvice({"com.microservice"})
public class GlobalExceptionHandler {
    @Autowired
    private Tracer tracer;
    @Value("${spring.application.name:default}")
    private String serviceName;
    @Value("${spring.profiles.active:dev}")
    private String profile;
    @Value("${robot.notice.enable:false}")
    private Boolean enableNotice;

    @ExceptionHandler({BusinessException.class})
    public RestResult businessExceptionHandler(BusinessException e) {
        log.warn("BusinessException:{}", e.getMessage());
        this.tracer.currentSpan().tag("请求参数异常", JSONObject.toJSONString(e));
        return RestResult.fail(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler({BaseException.class})
    public RestResult baseExceptionHandler(BaseException e) {
        log.warn("BaseException:{}", e.getMessage());
        this.tracer.currentSpan().tag("请求参数异常", JSONObject.toJSONString(e));
        return RestResult.fail(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler({ValidatorException.class})
    public RestResult validatorExceptionHandler(ValidatorException e) {
        log.warn("ValidatorException:{}", e.getMessage());
        this.tracer.currentSpan().tag("请求参数异常", JSONObject.toJSONString(e));
        return RestResult.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler({BindException.class})
    public RestResult validExceptionHandler(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.warn("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        this.tracer.currentSpan().tag("请求参数异常", HttpStatus.BAD_REQUEST.value() + "," + fieldError.getDefaultMessage());
        return RestResult.fail(HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public RestResult validMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.warn("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        this.tracer.currentSpan().tag("请求参数异常", HttpStatus.BAD_REQUEST.value() + "," + fieldError.getDefaultMessage());
        return RestResult.fail(HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public RestResult validMethodArgumentNotValidExceptionHandler(MissingServletRequestParameterException e) {
        this.tracer.currentSpan().tag("MissingServletRequestParameterException", e.getMessage());
        log.warn("丢失请求参数:{}({})", e.getMessage(), e.getParameterType());
        this.tracer.currentSpan().tag("请求参数异常", e.getMessage());
        return RestResult.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public RestResult httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        this.tracer.currentSpan().tag("HttpMessageNotReadableException", e.getMessage());
        log.warn("参数格式错误：{}", e.getMessage());
        this.tracer.currentSpan().tag("请求参数异常", e.getMessage());
        return RestResult.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public RestResult httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        this.tracer.currentSpan().tag("warn", e.getMessage());
        log.warn("MediaType不被支持:{}", e.getMessage());
        this.tracer.currentSpan().tag("请求参数异常", e.getMessage());
        return RestResult.fail(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
    }

    @ExceptionHandler({Exception.class})
    public RestResult otherExceptionHandler(Exception e) {
        this.tracer.currentSpan().tag("error", e.getMessage());
        if (this.enableNotice) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TraceId: ");
            stringBuilder.append(this.tracer.currentSpan().context().traceIdString());
            stringBuilder.append("\n");
            stringBuilder.append("ServiceName: ");
            stringBuilder.append(this.serviceName);
            stringBuilder.append("-").append(this.profile);
            stringBuilder.append("\n");
            stringBuilder.append("Exception: ");
            stringBuilder.append(e.getMessage());
            // 异常机器人发送消息
           /* DDRobot.sendError(stringBuilder.toString());*/
        }
        log.error("系统异常Exception:", e);
        e.printStackTrace();
        return RestResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统繁忙，请稍候再试");
    }
}
