package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import com.cbf.brasileiraoApi.dto.PlayerRequest;
import com.cbf.brasileiraoApi.dto.TeamRequest;
import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import com.cbf.brasileiraoApi.service.PlayerService;
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
@Tag(name = OpenApiConstants.PLAYER)
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<Player> savePlayer(@RequestBody PlayerRequest playerRequest){
        Player player = playerService.save(playerRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(player.getId())
                .toUri();
        return ResponseEntity.created(uri).body(player);
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll(){
        return ResponseEntity.ok().body(playerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable String id){
        return ResponseEntity.ok().body(playerService.findById(id));
    }
}
