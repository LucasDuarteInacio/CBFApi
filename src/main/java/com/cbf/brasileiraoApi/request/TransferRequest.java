package com.cbf.brasileiraoApi.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @NotBlank
    @Schema(description = "Player id",example = "64a64d0d-a790-4cac-97cf-e3cc44066c4b")
    private String playerId;
    @NotBlank
    @Schema(description = "Destination team id",example = "64a64d0d-a790-4cac-97cf-e3cc44066c4b")
    private String destinationTeamId;

    @NotBlank
    @Schema(description = "Tournament type",example = "2022-07-20")
    private LocalDate date;

    @NotBlank
    @Schema(description = "Tournament type",example = "20000000")
    private BigDecimal tranferValue;
}
