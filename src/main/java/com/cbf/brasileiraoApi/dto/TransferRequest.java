package com.cbf.brasileiraoApi.dto;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    private String playerId;
    private String originalTeamId;
    private String destinationTeamId;
    private LocalDate date;
    private BigDecimal tranferValue;
}
