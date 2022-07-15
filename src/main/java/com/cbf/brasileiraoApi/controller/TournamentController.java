package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("tournaments")
public class TournamentController {
    private final TournamentService tournamentService;

    @PostMapping
    public ResponseEntity<Tournament> saveTournament(@RequestBody Tournament tournament){
        Tournament tournament1 = tournamentService.save(tournament);
        return ResponseEntity.ok().body(tournament1);
    }
}
