package com.cbf.brasileiraoApi.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match  implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    private String id;
    private String description;
    private Tournament tournament;
    private Team homeTeam;
    private Team visitingTeam;
    private List<Event> events = new ArrayList<>();
    private Boolean deleted;

}
