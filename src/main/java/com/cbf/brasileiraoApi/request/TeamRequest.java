package com.cbf.brasileiraoApi.request;


import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequest {

    private String name;
    private String address;
}
