package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.TournamentResponseDTO;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.entity.enums.TournamentTypeEnum;
import com.cbf.brasileiraoApi.exception.BadRequestException;
import com.cbf.brasileiraoApi.exception.NotFoundException;
import com.cbf.brasileiraoApi.mapper.TournamentMapper;
import com.cbf.brasileiraoApi.repository.TournamentRepository;
import com.cbf.brasileiraoApi.request.TournamentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


import static com.cbf.brasileiraoApi.config.RedisConfig.CACHE_NAME;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class TournamentService {


    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;
    private final TeamService teamService;


    public TournamentResponseDTO save(TournamentRequest tournamentRequest) {
        valitadorTournamentType(tournamentRequest.getTournamentType());
        Tournament tournament = tournamentMapper.toDomain(tournamentRequest, teamService.findByIds(tournamentRequest.getTeamsId()));
        tournament.setDeleted(Boolean.FALSE);
        return tournamentMapper.toResponseDTO(tournamentRepository.save(tournament));
    }

    public void valitadorTournamentType(String tournamentTypeString) {
        try {
            TournamentTypeEnum.valueOf(tournamentTypeString);
        } catch (Exception e) {
            throw new BadRequestException(BadRequestException.tournamentBadRequest().getIssue());
        }
    }


    @Cacheable(
            cacheNames = CACHE_NAME,
            unless = "#result == null")
    public List<TournamentResponseDTO> findAll() {
        return tournamentMapper.toResponseDTO(tournamentRepository.findAllByDeletedFalse());
    }


    @Cacheable(
            cacheNames = CACHE_NAME,
            unless = "#result == null",
            key = "{#id}")
    public TournamentResponseDTO findById(String id) {
        return tournamentMapper.toResponseDTO(
                tournamentRepository.findById(id).orElseThrow(NotFoundException::tournamentNotFound)
        );
    }

    public TournamentResponseDTO update(String tournamentId, TournamentRequest tournamentRequest) {
        Tournament tournament = tournamentMapper.toDomain(findById(tournamentId));
        if (nonNull(tournamentRequest.getTournamentType())) {
            valitadorTournamentType(tournamentRequest.getTournamentType());
            tournament.setTournamentType(tournamentRequest.getTournamentType());
        } else if (nonNull(tournamentRequest.getName())) {
            tournament.setName(tournamentRequest.getName());
        } else if (nonNull(tournamentRequest.getTeamsId())) {
            tournament.setTeams(teamService.findByIds(tournamentRequest.getTeamsId()));
        }
        tournamentRepository.save(tournament);
        return tournamentMapper.toResponseDTO(tournament);
    }

    public void delete(String id) {
        Tournament tournament = tournamentMapper.toDomain(findById(id));
        tournament.setDeleted(Boolean.TRUE);
        tournamentRepository.save(tournament);
    }

}
