package com.cbf.brasileiraoApi.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private Issue issue;

    public ApiException(final Issue issue) {
        this.issue = issue;
    }

}
