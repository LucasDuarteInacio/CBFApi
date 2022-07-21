package com.cbf.brasileiraoApi.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseDTO {
    private String id;
    private String name;
    private String address;
    private List<PlayerResponseDTO> players;
    private Boolean deleted;
}
