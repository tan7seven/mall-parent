

package com.mall.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.mall.common.vo.RestResult;
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

    @Value("${spring.application.name:default}")
    private String serviceName;
    @Value("${spring.profiles.active:dev}")
    private String profile;
    @Value("${robot.notice.enable:false}")
    private Boolean enableNotice;

    @ExceptionHandler({BusinessException.class})
    public RestResult businessExceptionHandler(BusinessException e) {
        log.warn("BusinessException:{}", e.getMessage());
        return RestResult.failed(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler({BaseException.class})
    public RestResult baseExceptionHandler(BaseException e) {
        log.warn("BaseException:{}", e.getMessage());
        return RestResult.failed(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler({ValidatorException.class})
    public RestResult validatorExceptionHandler(ValidatorException e) {
        log.warn("ValidatorException:{}", e.getMessage());
        return RestResult.failed(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler({BindException.class})
    public RestResult validExceptionHandler(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.warn("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        return RestResult.failed(HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public RestResult validMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.warn("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        return RestResult.failed(HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public RestResult validMethodArgumentNotValidExceptionHandler(MissingServletRequestParameterException e) {
        log.warn("丢失请求参数:{}({})", e.getMessage(), e.getParameterType());
        return RestResult.failed(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public RestResult httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        log.warn("参数格式错误：{}", e.getMessage());
        return RestResult.failed(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public RestResult httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        log.warn("MediaType不被支持:{}", e.getMessage());
        return RestResult.failed(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
    }

    @ExceptionHandler({Exception.class})
    public RestResult otherExceptionHandler(Exception e) {
        if (this.enableNotice) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TraceId: ");
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
        return RestResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统繁忙，请稍候再试");
    }
}
