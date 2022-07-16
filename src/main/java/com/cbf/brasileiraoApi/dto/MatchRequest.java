package com.cbf.brasileiraoApi.dto;

import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequest {

    private String description;
    private String tournamentId;
    private String homeTeamId;
    private String visitingTeamId;

}
