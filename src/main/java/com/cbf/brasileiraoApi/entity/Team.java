package com.cbf.brasileiraoApi.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {

    @Id
    private String id;
    private String name;
    private String address;
    private Boolean deleted;
}
