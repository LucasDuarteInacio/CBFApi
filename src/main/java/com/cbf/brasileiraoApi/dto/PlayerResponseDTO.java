package com.cbf.brasileiraoApi.dto;


import com.cbf.brasileiraoApi.entity.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponseDTO {

    private String id;
    private String name;
    private LocalDate birthDate;
    private String country;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Team team;
    private Boolean deleted;


}
