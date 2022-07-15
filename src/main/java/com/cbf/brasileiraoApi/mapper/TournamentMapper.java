package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.TournamentRequest;
import com.cbf.brasileiraoApi.entity.Tournament;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    Tournament toDomain(TournamentRequest tournamentRequest);



}
