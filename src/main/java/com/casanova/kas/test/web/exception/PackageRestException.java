package com.casanova.kas.test.web.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PackageRestException extends BaseApiException {

    private static final long serialVersionUID = 3632751419384278178L;
    private static final String API_CODE = "REST-001";
    private static final String API_MESSAGE = "rest.error";
    private static final String ERROR_MESSAGE = "There was a problem consuming package rest service.";

    public PackageRestException() {
        super(ERROR_MESSAGE, null, API_CODE, API_MESSAGE);
    }

    public PackageRestException(final String msg) {
        super(msg, null, API_CODE, API_MESSAGE);
    }

    public PackageRestException(final Throwable cause) {
        super(ERROR_MESSAGE, cause, API_CODE, API_MESSAGE);
    }
}
