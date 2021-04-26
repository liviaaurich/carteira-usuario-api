package com.liviaaurich.carteiraservice.web.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private Integer status;
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private String detail;
}
