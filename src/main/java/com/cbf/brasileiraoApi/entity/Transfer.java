package com.cbf.brasileiraoApi.entity;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transfer  implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    private String id;
    private Team originalTeam;
    private Team destinationTeam;
    private Player player;
    private LocalDate date;
    private BigDecimal tranferValue;
    private Boolean deleted;
}
