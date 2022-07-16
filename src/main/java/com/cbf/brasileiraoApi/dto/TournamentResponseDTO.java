package com.cbf.brasileiraoApi.dto;


import com.cbf.brasileiraoApi.entity.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TournamentResponseDTO {
    private  String id;
    private String name;
    private String tournamentType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Team> teams;


}

