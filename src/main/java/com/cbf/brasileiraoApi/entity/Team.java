package com.cbf.brasileiraoApi.entity;


import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    private String id;
    private String name;
    private String address;
}
