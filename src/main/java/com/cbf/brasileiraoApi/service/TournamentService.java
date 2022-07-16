package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.TournamentRequest;
import com.cbf.brasileiraoApi.dto.TournamentResponseDTO;
import com.cbf.brasileiraoApi.dto.TransferResponseDTO;
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
    public TournamentResponseDTO save(TournamentRequest tournamentRequest){
        try {
            TournamentTypeEnum.valueOf(tournamentRequest.getTournamentType());
        }catch (Exception e ){
            throw new RuntimeException("tipo de torneio invalido");
        }

        Tournament tournament = tournamentMapper.toDomain(tournamentRequest,teamService.findByIds(tournamentRequest.getTeamsId()));


       return tournamentMapper.toResponseDTO(tournamentRepository.save(tournament));
    }

    public List<TournamentResponseDTO> findAll(){
        return tournamentMapper.toResponseDTO(tournamentRepository.findAll());
    }

    public TournamentResponseDTO findById(String id){
       Optional<Tournament> tournament = tournamentRepository.findById(id);
        if(tournament.isPresent()){
            return tournamentMapper.toResponseDTO(tournament.get());
        } else {
            throw new RuntimeException("Torneio não encontado");
        }
    }

}
