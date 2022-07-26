package com.cbf.brasileiraoApi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamResponseDTO  implements Serializable {
    private static final long serialVersionUID = -7717875185980104913L;
    private String id;
    private String name;
    private String address;
    private List<PlayerResponseDTO> players;
    private Boolean deleted;
}
