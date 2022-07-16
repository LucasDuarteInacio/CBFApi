package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.TeamResponseDTO;
import com.cbf.brasileiraoApi.dto.TournamentRequest;
import com.cbf.brasileiraoApi.dto.TournamentResponseDTO;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    @Mapping(target = "id",expression = "java(addUUID())")
    @Mapping(target = "teams",source = "teamsRequest")
    Tournament toDomain(TournamentRequest tournamentRequest,List<Team> teamsRequest);
    Tournament toDomain(TournamentResponseDTO tournamentResponseDTO);
    TournamentResponseDTO toResponseDTO (Tournament tournament);

    List<TournamentResponseDTO> toResponseDTO (List<Tournament> tournament);

    default String addUUID(){
        return String.valueOf(UUID.randomUUID());
    }



}
