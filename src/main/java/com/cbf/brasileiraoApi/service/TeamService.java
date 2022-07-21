package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.TeamResponseDTO;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.exception.NotFoundException;
import com.cbf.brasileiraoApi.mapper.TeamMapper;
import com.cbf.brasileiraoApi.repository.TeamRepository;
import com.cbf.brasileiraoApi.request.TeamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final PlayerService playerService;

    public List<Team> findByIds(List<String> teamsId) {
        return teamRepository.findAllByIdAndDeletedFalse(teamsId);
    }

    public Team save(TeamRequest teamRequest) {
        Team team = teamMapper.toDomain(teamRequest);
        team.setDeleted(Boolean.FALSE);
        return teamRepository.save(team);
    }

    public List<TeamResponseDTO> findAll() {
        List<Team> teams = teamRepository.findAllByDeletedFalse();
        List<TeamResponseDTO> teamResponseDTOList = teamMapper.toResponseDTO(teams);
        for (TeamResponseDTO teamResponseDTO : teamResponseDTOList) {
            teamResponseDTO.setPlayers(playerService.findAllByTeamId(teamResponseDTO.getId()));
        }
        return teamResponseDTOList;
    }

    public Team findById(String id) {
        return teamRepository.findById(id).orElseThrow(NotFoundException::teamNotFound);
    }

    public TeamResponseDTO update(String id, TeamRequest teamRequest) {
        Team team = findById(id);
        if (nonNull(teamRequest.getName())) {
            team.setName(teamRequest.getName());
        }
        if (nonNull(teamRequest.getAddress())) {
            team.setAddress(teamRequest.getAddress());
        }

        return teamMapper.toResponseDTO(teamRepository.save(team));
    }

    public void delete(String id) {
        Team team = findById(id);
        team.setDeleted(Boolean.TRUE);
        teamRepository.save(team);
    }

}
