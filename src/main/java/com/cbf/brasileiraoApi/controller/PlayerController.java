package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import com.cbf.brasileiraoApi.request.PlayerRequest;
import com.cbf.brasileiraoApi.dto.PlayerResponseDTO;
import com.cbf.brasileiraoApi.service.PlayerService;
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
@RequestMapping("players")
@Tag(name = OpenApiConstants.PLAYER, description = "information about players")
public class PlayerController {

    private final PlayerService playerService;


    @PostMapping
    @Operation(summary = "Register new player")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Register new player")
            }
    )
    public ResponseEntity<PlayerResponseDTO> newPlayer(@RequestBody PlayerRequest playerRequest){
        PlayerResponseDTO player = playerService.save(playerRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(player.getId())
                .toUri();
        return ResponseEntity.created(uri).body(player);
    }

    @GetMapping
    @Operation(summary = "find all players")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "find all players")
            }
    )
    public ResponseEntity<List<PlayerResponseDTO>> findAll(){
        return ResponseEntity.ok().body(playerService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find player by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Find player by id"),
                    @ApiResponse(responseCode = "404", description = "Player not found")
            }
    )
    public ResponseEntity<PlayerResponseDTO> findById(@PathVariable String id){
        return ResponseEntity.ok().body(playerService.findById(id));
    }
}
