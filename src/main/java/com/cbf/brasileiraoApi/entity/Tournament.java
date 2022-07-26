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
public class Tournament implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String tournamentType;
    private List<Team> teams = new ArrayList<>();
    private Boolean deleted;
}
