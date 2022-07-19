package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import com.cbf.brasileiraoApi.request.TeamRequest;
import com.cbf.brasileiraoApi.dto.TeamResponseDTO;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.service.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("teams")
@Tag(name = OpenApiConstants.TEAM, description = "information about teams")
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<Team> saveTeam(@RequestBody TeamRequest teamRequest){
        Team team =teamService.save(teamRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(team.getId())
                .toUri();
        return ResponseEntity.created(uri).body(team);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> findAll(){
        return ResponseEntity.ok().body(teamService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable String id){
        return ResponseEntity.ok().body(teamService.findById(id));
    }

}
