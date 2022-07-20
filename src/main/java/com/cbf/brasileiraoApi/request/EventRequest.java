package com.cbf.brasileiraoApi.request;

import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    @Schema(description = "Player id",example = "88b16c54-a20d-4e20-85dc-e031cd693f31")
    private String playerId;

    @Schema(description = "player id to be changed",example = "88b16c54-a20d-4e20-85dc-e031cd693f31")
    private String playerOutId;

    @Schema(description = "minutes of addition",example = "00:03")
    private String addition;

    @Schema(description = "minutes of match",example = "45:03")
    private String minutes;
}