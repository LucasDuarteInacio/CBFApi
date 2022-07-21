package com.cbf.brasileiraoApi.dto;


import com.cbf.brasileiraoApi.entity.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferResponseDTO {

    private String id;
    private Team originalTeam;
    private Team destinationTeam;
    private PlayerResponseDTO player;
    private LocalDate date;
    private BigDecimal tranferValue;
    private Boolean deleted;
}
