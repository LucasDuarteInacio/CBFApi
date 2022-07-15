package com.cbf.brasileiraoApi.entity;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    @Id
    private String id;
    private String originalTeam;
    private String destinationTeam;
    private LocalDate date;
    private BigDecimal tranferValue;
}
