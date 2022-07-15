package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import com.cbf.brasileiraoApi.dto.TournamentRequest;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.service.TournamentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tournaments")
@Tag(name = OpenApiConstants.TOURNAMENT)
public class TournamentController {
    private final TournamentService tournamentService;

    @PostMapping
    public ResponseEntity<Tournament> saveTournament(@RequestBody  TournamentRequest tournamentRequest){
       Tournament tournament = tournamentService.save(tournamentRequest);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/id")
                        .buildAndExpand(tournament.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(tournament);
    }

    @GetMapping
    public ResponseEntity<List<Tournament>> findAll(){
        return ResponseEntity.ok().body(tournamentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> findById(@PathVariable String id){
        return ResponseEntity.ok().body(tournamentService.findById(id));
    }
}
