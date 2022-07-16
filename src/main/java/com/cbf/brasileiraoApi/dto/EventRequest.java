package com.cbf.brasileiraoApi.dto;

import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    private String playerId;
    private String playerOutId;
    private String addition;
    private String minutes;
}