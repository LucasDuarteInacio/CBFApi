package com.cbf.brasileiraoApi.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player  implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    private String id;
    private String name;
    private LocalDate birthDate;
    private String country;
    private Team team;
    private Boolean deleted;

}
