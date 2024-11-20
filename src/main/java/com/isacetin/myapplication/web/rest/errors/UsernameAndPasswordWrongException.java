package com.isacetin.myapplication.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import tech.jhipster.web.rest.errors.ProblemDetailWithCause;

import java.net.URI;

public class UsernameAndPasswordWrongException extends ErrorResponseException {

    private final String entityName;

    private final String errorKey;

    public UsernameAndPasswordWrongException(String defaultMessage, String entityName, String errorKey) {
        this(ErrorConstants.DEFAULT_TYPE, defaultMessage, entityName, errorKey);
    }

    public UsernameAndPasswordWrongException(URI type, String defaultMessage, String entityName, String errorKey) {
        super(
            HttpStatus.BAD_REQUEST,
            ProblemDetailWithCause.ProblemDetailWithCauseBuilder.instance()
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withType(type)
                .withDetail("Wrong username or password")
                .withTitle(defaultMessage)
                .withProperty("message", "error." + errorKey)
                .withProperty("params", entityName)
                .build(),
            null
        );
        this.entityName = entityName;
        this.errorKey = errorKey;
    }
}
