package com.cbf.brasileiraoApi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private Player player;
    private Player playerOut;
    private String addition;
    private String minutes;
    private String eventType;
}