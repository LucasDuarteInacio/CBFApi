package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.PlayerRequest;
import com.cbf.brasileiraoApi.dto.PlayerResponseDTO;
import com.cbf.brasileiraoApi.dto.TeamRequest;
import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    Player toDomain(PlayerRequest playerRequest);
    PlayerResponseDTO toReponseDTO(Player player);

    @IterableMapping(qualifiedByName="mapWithoutTeam")
    List<PlayerResponseDTO> toReponseDTO(List<Player> player);

    @Named("mapWithoutTeam")
    @Mapping(target = "team", ignore = true)
    PlayerResponseDTO toReponseDTOWithoutTeam(Player player);

}
