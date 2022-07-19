package com.cbf.brasileiraoApi.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandardError {

    private String message;
    private Long timesTamp;
    private String path;
}
