package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.TeamRequest;
import com.cbf.brasileiraoApi.entity.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    Team toDomain(TeamRequest teamRequest);

}
