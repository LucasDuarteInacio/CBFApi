package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.TournamentRequest;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.entity.enums.TournamentTypeEnum;
import com.cbf.brasileiraoApi.mapper.TournamentMapper;
import com.cbf.brasileiraoApi.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;
    private final TeamService teamService;
    public Tournament save(TournamentRequest tournamentRequest){
        try {
            TournamentTypeEnum.valueOf(tournamentRequest.getTournamentType());
        }catch (Exception e ){
            throw new RuntimeException("tipo de torneio invalido");
        }
        String id = String.valueOf(UUID.randomUUID());
        List<Team> teams = teamService.findByIds(tournamentRequest.getTeamsId());
        Tournament tournament = tournamentMapper.toDomain(tournamentRequest);
        tournament.setTeams(teams);
        tournament.setId(id);
       return tournamentRepository.save(tournament);
    }

    public List<Tournament> findAll(){
        return tournamentRepository.findAll();
    }

    public Tournament findById(String id){
       Optional<Tournament> tournament = tournamentRepository.findById(id);
        if(tournament.isPresent()){
            return tournament.get();
        } else {
            throw new RuntimeException("Torneio não encontado");
        }
    }

}
