package com.cbf.brasileiraoApi.entity;

import com.cbf.brasileiraoApi.enums.tournamentType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {

    @Id
    private String id;
    private String name;
    private Enum<tournamentType> tournamentType;
    private List<Team> teams = new ArrayList<>();
}
