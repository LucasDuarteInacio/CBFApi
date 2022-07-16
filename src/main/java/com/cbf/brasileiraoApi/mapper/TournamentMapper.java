package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.TeamResponseDTO;
import com.cbf.brasileiraoApi.dto.TournamentRequest;
import com.cbf.brasileiraoApi.dto.TournamentResponseDTO;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    Tournament toDomain(TournamentRequest tournamentRequest);
    Tournament toDomain(TournamentResponseDTO tournamentResponseDTO);
    TournamentResponseDTO toResponseDTO (Tournament tournament);

    List<TournamentResponseDTO> toResponseDTO (List<Tournament> tournament);



}
