package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.MatchResponseDTO;
import com.cbf.brasileiraoApi.entity.Match;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.request.MatchRequest;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    @Named("tournamentWithoutTeam")
    @Mapping(target = "tournament", source = "tournamentRequest")
    @Mapping(target = "visitingTeam", source = "visitingTeamRequest")
    @Mapping(target = "homeTeam", source = "homeTeamRequest")
    @Mapping(target = "deleted", source = "isDeleted")
    @Mapping(target = "id", expression = "java(addUUID())")
    Match toDomain(MatchRequest matchRequest, Team homeTeamRequest, Team visitingTeamRequest, Tournament tournamentRequest, Boolean isDeleted);

    MatchResponseDTO toReponseDTO(Match match);

    @IterableMapping(qualifiedByName = "mapWithoutTournament")
    List<MatchResponseDTO> toReponseDTOWithoutTournament(List<Match> matches);

    @Named("mapWithoutTournament")
    @Mapping(target = "tournament", ignore = true)
    MatchResponseDTO toReponseDTOWithoutTournament(Match match);

    default String addUUID() {
        return String.valueOf(UUID.randomUUID());
    }


}
