package com.cbf.brasileiraoApi.dto;

import com.cbf.brasileiraoApi.entity.Event;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchResponseDTO  implements Serializable {
    private static final long serialVersionUID = 4213090157579577235L;

    private String id;
    private String description;
    private Tournament tournament;
    private Team homeTeam;
    private Team visitingTeam;
    private List<Event> events = new ArrayList<>();
    private Boolean deleted;

}
