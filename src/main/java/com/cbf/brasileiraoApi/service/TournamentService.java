package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    public Tournament save(Tournament tournament){
       return tournamentRepository.save(tournament);
    }

}
