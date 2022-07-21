package com.cbf.brasileiraoApi.mapper;

import com.cbf.brasileiraoApi.dto.TransferResponseDTO;
import com.cbf.brasileiraoApi.entity.Transfer;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransferMapper {


    Transfer toDomain(TransferResponseDTO transferResponseDTO);


    @IterableMapping(qualifiedByName = "mapWithoutTeam")
    List<TransferResponseDTO> toReponseDTO(List<Transfer> transfer);

    @Named("mapWithoutTeam")
    @Mapping(target = "player.team", ignore = true)
    TransferResponseDTO toReponseDTOWithoutTeam(Transfer transfer);

}
