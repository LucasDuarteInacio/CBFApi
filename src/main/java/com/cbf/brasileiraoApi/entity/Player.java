package com.cbf.brasileiraoApi.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    @Id
    private String id;
    private String name;
    private LocalDate birthDate;
    private String country;
    private Team team;
    private Boolean deleted;

}
