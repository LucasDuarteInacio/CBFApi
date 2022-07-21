package com.cbf.brasileiraoApi.dto;


import com.cbf.brasileiraoApi.entity.Team;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDTO {

    private String id;
    private Team originalTeam;
    private Team destinationTeam;
    private PlayerResponseDTO player;
    private LocalDate date;
    private BigDecimal tranferValue;
    private Boolean deleted;
}
