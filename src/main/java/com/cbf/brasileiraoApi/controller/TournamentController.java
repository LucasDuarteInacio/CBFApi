package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import com.cbf.brasileiraoApi.request.TournamentRequest;
import com.cbf.brasileiraoApi.dto.TournamentResponseDTO;
import com.cbf.brasileiraoApi.service.TournamentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Tag(name = OpenApiConstants.TOURNAMENT, description = "Tournament about information")
public class TournamentController {
    private final TournamentService tournamentService;

    @PostMapping
    @Operation(summary = "Register new tournament")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Register new Tournament")
            }
    )
    public ResponseEntity<TournamentResponseDTO> newTournament(@RequestBody  TournamentRequest tournamentRequest){
        TournamentResponseDTO tournament = tournamentService.save(tournamentRequest);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/id")
                        .buildAndExpand(tournament.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(tournament);
    }

    @GetMapping
    @Operation(summary = "find all tournaments")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "find all tournaments")
            }
    )
    public ResponseEntity<List<TournamentResponseDTO>> findAll(){
        return ResponseEntity.ok().body(tournamentService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find tournament by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Find tournament by id"),
                    @ApiResponse(responseCode = "404", description = "Tournaments not found")
            }
    )
    public ResponseEntity<TournamentResponseDTO> findById(@PathVariable String id){
        return ResponseEntity.ok().body(tournamentService.findById(id));
    }
}
