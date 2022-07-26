package com.cbf.brasileiraoApi.dto;


import com.cbf.brasileiraoApi.entity.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferResponseDTO  implements Serializable {

    private static final long serialVersionUID = 1347789388048590349L;
    private String id;
    private Team originalTeam;
    private Team destinationTeam;
    private PlayerResponseDTO player;
    private LocalDate date;
    private BigDecimal tranferValue;
    private Boolean deleted;
}
