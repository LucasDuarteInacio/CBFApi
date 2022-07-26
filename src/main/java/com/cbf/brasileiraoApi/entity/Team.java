package com.cbf.brasileiraoApi.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team  implements Serializable {


    private static final long serialVersionUID = -1343258859675853580L;
    @Id
    private String id;
    private String name;
    private String address;
    private Boolean deleted;
}
