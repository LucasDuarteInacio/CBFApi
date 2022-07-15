package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.TeamRequest;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.mapper.TeamMapper;
import com.cbf.brasileiraoApi.repository.TeamRepository;
import com.cbf.brasileiraoApi.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public Team save(TeamRequest teamRequest){
        String id = String.valueOf(UUID.randomUUID());
        Team team = teamMapper.toDomain(teamRequest);
        team.setId(id);
       return teamRepository.save(team);
    }

}
