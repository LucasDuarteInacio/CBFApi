package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.TeamRequest;
import com.cbf.brasileiraoApi.dto.TeamResponseDTO;
import com.cbf.brasileiraoApi.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    Team toDomain(TeamRequest teamRequest);


    List<TeamResponseDTO> toResponseDTO (List<Team> teams);

}
