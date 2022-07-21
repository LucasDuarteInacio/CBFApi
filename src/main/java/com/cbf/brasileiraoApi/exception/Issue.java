package com.cbf.brasileiraoApi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Issue implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty
    private final String message;
    @JsonProperty
    private List<String> details;

    public Issue(final IssueEnum issue) {
        this.message = issue.getMessage();
    }


}
