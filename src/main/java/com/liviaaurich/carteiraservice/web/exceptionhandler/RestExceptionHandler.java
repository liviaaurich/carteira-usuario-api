package com.liviaaurich.carteiraservice.web.exceptionhandler;

import com.liviaaurich.carteiraservice.service.util.ConstantsUtil;
import com.liviaaurich.carteiraservice.web.rest.errors.ParametrizedMessageException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ParametrizedMessageException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleParametrizedMessage(ParametrizedMessageException ex, WebRequest request) {
        ApiError error = apiError(HttpStatus.BAD_REQUEST, ConstantsUtil.ERROR_TITLE, ex.getCode(),
                ConstantsUtil.PARAMETERIZED_TYPE).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError error = apiError(status, ConstantsUtil.MENSAGEM_INCOMPREENSIVEL, ConstantsUtil.CORPO_INVALIDO,
                ConstantsUtil.DEFAULT_TYPE).build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError error = apiError(status, ConstantsUtil.ARGUMENTO_INVALIDO, ex.getMessage(),
                ConstantsUtil.DEFAULT_TYPE).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if(Objects.isNull(body)) {
            body = ApiError.builder().title(status.getReasonPhrase())
                    .status(status.value()).build();
        } else if(body instanceof String) {
            body = ApiError.builder().title((String) body)
                    .status(status.value()).build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    ApiError.ApiErrorBuilder apiError(HttpStatus status, String title, String detail, String type) {
        return ApiError.builder().status(status.value()).title(title)
                .detail(detail).timestamp(LocalDateTime.now()).type(type);
    }

}
