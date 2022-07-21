package com.cbf.brasileiraoApi.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TournamentRequest {

    @NotBlank
    @Schema(description = "Tournament name", example = "Campeonato Brasileiro")
    private String name;

    @NotBlank
    @Schema(description = "Tournament type", example = "CHAMPIONSHIP")
    private String tournamentType;


    @Schema(description = "List Teams id")
    private List<String> teamsId = new ArrayList<>();


}

