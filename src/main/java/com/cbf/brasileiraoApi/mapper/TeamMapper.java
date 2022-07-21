package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.TeamResponseDTO;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.request.TeamRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "id", expression = "java(addUUID())")
    Team toDomain(TeamRequest teamRequest);

    List<TeamResponseDTO> toResponseDTO(List<Team> teams);

    TeamResponseDTO toResponseDTO(Team team);

    default String addUUID() {
        return String.valueOf(UUID.randomUUID());
    }

}
