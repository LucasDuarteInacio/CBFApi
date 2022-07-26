package com.cbf.brasileiraoApi.dto;


import com.cbf.brasileiraoApi.entity.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TournamentResponseDTO  implements Serializable {

    private static final long serialVersionUID = -917424264038380356L;
    private String id;
    private String name;
    private String tournamentType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Team> teams;
    private Boolean deleted;
}

