package com.cbf.brasileiraoApi.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequest {

    @NotBlank
    @Schema(description = "Player name", example = "Lucas")
    private String name;

    @NotBlank
    @Schema(description = "Birth date", example = "1994-11-25")
    private LocalDate birthDate;

    @NotBlank
    @Schema(description = "Country of birth", example = "Brasil")
    private String country;

    @NotNull
    @Schema(description = "Team id", example = "88b16c54-a20d-4e20-85dc-e031cd693f31")
    private String idTeam;

}
