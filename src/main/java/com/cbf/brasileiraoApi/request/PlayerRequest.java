package com.cbf.brasileiraoApi.request;


import com.cbf.brasileiraoApi.entity.Team;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequest {

    private String name;
    private LocalDate birthDate;
    private String country;
    private String idTeam;

}
