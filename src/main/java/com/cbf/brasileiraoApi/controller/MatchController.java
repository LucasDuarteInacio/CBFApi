package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import com.cbf.brasileiraoApi.dto.MatchResponseDTO;
import com.cbf.brasileiraoApi.dto.PlayerResponseDTO;
import com.cbf.brasileiraoApi.entity.Event;
import com.cbf.brasileiraoApi.entity.Match;
import com.cbf.brasileiraoApi.mapper.MatchMapper;
import com.cbf.brasileiraoApi.request.EventRequest;
import com.cbf.brasileiraoApi.request.MatchRequest;
import com.cbf.brasileiraoApi.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("matchers")
@Tag(name = OpenApiConstants.MATCH, description = "information about matches")
public class MatchController {
    private final MatchService matchService;
    private final MatchMapper matchMapper;

    @PostMapping
    @Operation(summary = "Register new match")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Register new match")
            }
    )
    public ResponseEntity<MatchResponseDTO> newMatch(@RequestBody @Valid MatchRequest matchRequest) {
        MatchResponseDTO match = matchMapper.toReponseDTO(matchService.newMatch(matchRequest));
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/id")
                        .buildAndExpand(match.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(match);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find match by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Find match by id"),
                    @ApiResponse(responseCode = "404", description = "Match not found")
            }
    )
    public ResponseEntity<MatchResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(matchMapper.toReponseDTO(matchService.findById(id)));
    }

    @Operation(summary = "Add event by matchId and typeEvent")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Add event by matchId and typeEvent"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "404", description = "Match not found")
            }
    )
    @PostMapping("/{id}/events/{typeEvent}")
    public ResponseEntity<Event> event(@PathVariable String id, @PathVariable String typeEvent, @RequestBody EventRequest eventRequest) {
        Event event = matchService.newEvent(id, typeEvent, eventRequest);
        return ResponseEntity.ok().body(event);
    }

    @GetMapping("tournaments/{tournamentId}")
    @Operation(summary = "Find player by tournament id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Find player by tournament id"),
            }
    )
    public ResponseEntity<List<MatchResponseDTO>> findByTournamentId(@PathVariable String tournamentId) {
        return ResponseEntity.ok().body(matchMapper.toReponseDTOWithoutTournament(matchService.findAllByTournamentId(tournamentId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete match by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Delete match by id"),
                    @ApiResponse(responseCode = "404", description = "Match not found")
            }
    )
    public ResponseEntity<Void> delete(@PathVariable String id) {
        matchService.delete(id);
        return ResponseEntity.ok().build();
    }
}
