package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.PlayerResponseDTO;
import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.request.PlayerRequest;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "team", source = "teamRequest")
    @Mapping(target = "name", source = "playerRequest.name")
    @Mapping(target = "id", expression = "java(addUUID())")
    Player toDomain(PlayerRequest playerRequest, Team teamRequest);

    Player toDomain(PlayerResponseDTO playerResponseDTO);

    PlayerResponseDTO toReponseDTO(Player player);

    List<PlayerResponseDTO> toReponseDTO(List<Player> player);

    @IterableMapping(qualifiedByName = "mapWithoutTeam")
    List<PlayerResponseDTO> toReponseDTOWithoutTeam(List<Player> player);

    @Named("mapWithoutTeam")
    @Mapping(target = "team", ignore = true)
    PlayerResponseDTO toReponseDTOWithoutTeam(Player player);

    default String addUUID() {
        return String.valueOf(UUID.randomUUID());
    }

}
