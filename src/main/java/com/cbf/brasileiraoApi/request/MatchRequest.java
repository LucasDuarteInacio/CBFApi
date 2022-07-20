package com.cbf.brasileiraoApi.request;

import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequest {

    @NotBlank
    @Schema(description = "Description match", example = "Rodada 1/38")
    private String description;

    @NotBlank
    @Schema(description = "Tournament id", example = "128a3ae0-c80a-48c7-b85f-d355f58f6210")
    private String tournamentId;

    @NotBlank
    @Schema(description = "Team id that is playing at home", example = "128a3ae0-c80a-48c7-b85f-d355f58f6210")
    private String homeTeamId;

    @NotBlank
    @Schema(description = "Team id that is playing away from home", example = "128a3ae0-c80a-48c7-b85f-d355f58f6210")
    private String visitingTeamId;

}
