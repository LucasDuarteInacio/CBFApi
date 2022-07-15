package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.PlayerRequest;
import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.mapper.PlayerMapper;
import com.cbf.brasileiraoApi.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    @Lazy
    private final TeamService teamService;

    public Player save(PlayerRequest playerRequest) {
        String id = String.valueOf(UUID.randomUUID());
        Team team = teamService.findById(playerRequest.getIdTeam());
        Player player = playerMapper.toDomain(playerRequest);
        player.setId(id);
        player.setTeam(team);
        return playerRepository.save(player);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findById(String id) {
        return playerRepository.findById(id).get();
    }

    public List<Player> findAllByIdTeam(String idTeam) {
        return playerRepository.findAllByTeamId(idTeam);
    }

    public void changePlayerTeam(Player player, Team team) {
        player.setTeam(team);
        playerRepository.save(player);
    }

}
