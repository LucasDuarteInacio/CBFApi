package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.exception.BadRequestException;
import com.cbf.brasileiraoApi.exception.NotFoundException;
import com.cbf.brasileiraoApi.request.TournamentRequest;
import com.cbf.brasileiraoApi.dto.TournamentResponseDTO;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.entity.enums.TournamentTypeEnum;
import com.cbf.brasileiraoApi.mapper.TournamentMapper;
import com.cbf.brasileiraoApi.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            throw new BadRequestException(BadRequestException.tournamentBadRequest().getIssue());
        }

        Tournament tournament = tournamentMapper.toDomain(tournamentRequest,teamService.findByIds(tournamentRequest.getTeamsId()));


       return tournamentMapper.toResponseDTO(tournamentRepository.save(tournament));
    }

    public List<TournamentResponseDTO> findAll(){
        return tournamentMapper.toResponseDTO(tournamentRepository.findAll());
    }

    public TournamentResponseDTO findById(String id){
       return tournamentMapper.toResponseDTO(
               tournamentRepository.findById(id).orElseThrow(NotFoundException::tournamentNotFound)
       );
    }

}
