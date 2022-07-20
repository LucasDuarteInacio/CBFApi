package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.PlayerResponseDTO;
import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.exception.BadRequestException;
import com.cbf.brasileiraoApi.exception.NotFoundException;
import com.cbf.brasileiraoApi.mapper.PlayerMapper;
import com.cbf.brasileiraoApi.repository.PlayerRepository;
import com.cbf.brasileiraoApi.request.PlayerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    @Lazy
    private final TeamService teamService;

    public PlayerResponseDTO save(PlayerRequest playerRequest) {
        Player player = playerMapper.toDomain(playerRequest, teamService.findById(playerRequest.getIdTeam()));
        player.setDeleted(Boolean.FALSE);
        return playerMapper.toReponseDTO(playerRepository.save(player));
    }

    public List<PlayerResponseDTO> findAll() {
        return playerMapper.toReponseDTO(playerRepository.findAllByDeletedFalse());
    }

    public PlayerResponseDTO findById(String id) {
        return playerMapper.toReponseDTO(playerRepository.findById(id).orElseThrow(NotFoundException::playerNotFound));
    }

    public List<PlayerResponseDTO> findAllByTeamId(String idTeam) {
        return playerMapper.toReponseDTOWithoutTeam(playerRepository.findAllByTeamId(idTeam));
    }

    public PlayerResponseDTO updateById(String id, PlayerRequest playerRequest) {
        if(isPlayerDeleted(id)){
            throw new BadRequestException(BadRequestException.playerBadRequest().getIssue());
        }
        PlayerResponseDTO playerResponseDTO = findById(id);
        if(nonNull(playerRequest.getName())){
            playerResponseDTO.setName(playerRequest.getName());
        }
        if(nonNull(playerRequest.getBirthDate())){
            playerResponseDTO.setBirthDate(playerRequest.getBirthDate());
        }
        if(nonNull(playerRequest.getCountry())){
            playerResponseDTO.setCountry(playerRequest.getCountry());
        }
        playerRepository.save(playerMapper.toDomain(playerResponseDTO));
        return playerResponseDTO;
    }

    public Boolean isPlayerDeleted(String playerId){
       return nonNull(playerRepository.findByIdAndDeletedIsTrue(playerId));

    }

    public void deletePlayer(String playerId){
        Player player = playerMapper.toDomain(findById(playerId));
        player.setDeleted(Boolean.TRUE);
        playerRepository.save(player);
    }


    public void changePlayerTeam(Player player, Team team) {
        player.setTeam(team);
        playerRepository.save(player);
    }


}
