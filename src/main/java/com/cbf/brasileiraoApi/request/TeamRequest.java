package com.cbf.brasileiraoApi.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequest {

    @NotBlank
    @Schema(description = "Team name",example = "Cruzeiro")
    private String name;

    @NotBlank
    @Schema(description = "Team address",example = "Belo Horizonte, Brasil")
    private String address;
}
