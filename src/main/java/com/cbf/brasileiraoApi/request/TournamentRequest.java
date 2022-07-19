package com.cbf.brasileiraoApi.request;


import com.cbf.brasileiraoApi.entity.enums.TournamentTypeEnum;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TournamentRequest {

    private String name;
    private String tournamentType;
    private List<String> teamsId = new ArrayList<>();


}

