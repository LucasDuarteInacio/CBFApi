package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.TeamRequest;
import com.cbf.brasileiraoApi.dto.TeamResponseDTO;
import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.mapper.PlayerMapper;
import com.cbf.brasileiraoApi.mapper.TeamMapper;
import com.cbf.brasileiraoApi.repository.TeamRepository;
import com.cbf.brasileiraoApi.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    @Transactional(readOnly = true)
    public List<Team> findByIds(List<String> teamsId){
        return (List<Team>) teamRepository.findAllById(teamsId);
    }
    public Team save(TeamRequest teamRequest){
        Team team = teamMapper.toDomain(teamRequest);
       return teamRepository.save(team);
    }


    public List<TeamResponseDTO> findAll(){
        List<Team> teams = teamRepository.findAll();
        List<TeamResponseDTO> teamResponseDTOList = teamMapper.toResponseDTO(teams);
        for(TeamResponseDTO teamResponseDTO:teamResponseDTOList){
            teamResponseDTO.setPlayers(playerMapper.toReponseDTOWithoutTeam(playerService.findAllByIdTeam(teamResponseDTO.getId())));
        }
        return teamResponseDTOList;
    }

    public Team findById(String id){
        return teamRepository.findById(id).get();
    }

}
