package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.MatchRequest;
import com.cbf.brasileiraoApi.dto.TournamentResponseDTO;
import com.cbf.brasileiraoApi.entity.Match;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.repository.TournamentRepository;
import com.cbf.brasileiraoApi.service.TournamentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    @Named("tournamentWithoutTeam")
    @Mapping(target = "tournament", source = "tournamentRequest")
    @Mapping(target = "visitingTeam", source = "visitingTeamRequest")
    @Mapping(target = "homeTeam", source = "homeTeamRequest")
    @Mapping(target = "id",expression = "java(addUUID())")
    Match toDomain(MatchRequest matchRequest, Team homeTeamRequest, Team visitingTeamRequest, Tournament tournamentRequest);

    default String addUUID(){
        return String.valueOf(UUID.randomUUID());
    }


}
