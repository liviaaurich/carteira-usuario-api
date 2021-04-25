package com.liviaaurich.carteiraservice.web.rest.errors;

public class ParametrizedMessageException extends RuntimeException {
    private final String code;
    private final String titleCode;
    private static final long serialVersionUID = 1L;

    public ParametrizedMessageException(String code, String titleCode) {
        this.code = code;
        this.titleCode = titleCode;
    }

    public ParametrizedMessageException(String code, String titleCode, Exception exception) {
        super(exception);
        this.code = code;
        this.titleCode = titleCode;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitleCode() {
        return this.titleCode;
    }
}
