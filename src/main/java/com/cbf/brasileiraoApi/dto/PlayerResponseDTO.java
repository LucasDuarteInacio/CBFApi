package com.cbf.brasileiraoApi.dto;


import com.cbf.brasileiraoApi.entity.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerResponseDTO  implements Serializable {


    private static final long serialVersionUID = -5709304311686751041L;
    private String id;
    private String name;
    private LocalDate birthDate;
    private String country;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Team team;
    private Boolean deleted;


}
