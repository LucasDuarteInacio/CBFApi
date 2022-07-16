package com.cbf.brasileiraoApi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    private Player player;
    private Player playerOut;
    private String addition;
    private String minutes;
    private String infractionType;
    private String eventType;
}