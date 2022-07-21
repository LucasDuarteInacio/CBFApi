package com.cbf.brasileiraoApi.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    private String id;
    private String description;
    private Tournament tournament;
    private Team homeTeam;
    private Team visitingTeam;
    private List<Event> events = new ArrayList<>();
    private Boolean deleted;

}
