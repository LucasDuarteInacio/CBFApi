package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.exception.NotFoundException;
import com.cbf.brasileiraoApi.request.PlayerRequest;
import com.cbf.brasileiraoApi.dto.PlayerResponseDTO;
import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.mapper.PlayerMapper;
import com.cbf.brasileiraoApi.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    @Lazy
    private final TeamService teamService;

    public PlayerResponseDTO save(PlayerRequest playerRequest) {
        Player player = playerMapper.toDomain(playerRequest,teamService.findById(playerRequest.getIdTeam()));
        return playerMapper.toReponseDTO(playerRepository.save(player));
    }

    public List<PlayerResponseDTO> findAll() {
        return playerMapper.toReponseDTO(playerRepository.findAll());
    }

    public PlayerResponseDTO findById(String id) {
        return  playerMapper.toReponseDTO(playerRepository.findById(id).orElseThrow(NotFoundException::playerNotFound));
    }

    public List<Player> findAllByIdTeam(String idTeam) {
        return playerRepository.findAllByTeamId(idTeam);
    }

    public void changePlayerTeam(Player player, Team team) {
        player.setTeam(team);
        playerRepository.save(player);
    }

}
