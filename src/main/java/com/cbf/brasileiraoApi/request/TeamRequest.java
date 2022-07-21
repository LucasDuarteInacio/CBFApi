package com.cbf.brasileiraoApi.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequest {

    @NotBlank
    @Schema(description = "Team name", example = "Cruzeiro")
    private String name;

    @NotBlank
    @Schema(description = "Team address", example = "Belo Horizonte, Brasil")
    private String address;
}
