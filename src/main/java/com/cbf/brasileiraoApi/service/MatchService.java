package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.MatchRequest;
import com.cbf.brasileiraoApi.entity.Match;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.mapper.MatchMapper;
import com.cbf.brasileiraoApi.mapper.TournamentMapper;
import com.cbf.brasileiraoApi.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final TournamentMapper tournamentMapper;
    private final TeamService teamService;
    private final TournamentService tournamentService;

    public Match newMatch(MatchRequest matchRequest){
        Match match = matchMapper.toDomain(
                matchRequest,
                teamService.findById(matchRequest.getHomeTeamId()),
                teamService.findById(matchRequest.getVisitingTeamId()),
                tournamentMapper.toDomain(tournamentService.findById(matchRequest.getTournamentId()))
        );
      return  matchRepository.save(match);
    }

    public Match findById(String id){
        return matchRepository.findById(id).get();
    }
}
