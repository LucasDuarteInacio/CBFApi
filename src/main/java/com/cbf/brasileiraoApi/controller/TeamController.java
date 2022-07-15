package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.dto.TeamRequest;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("teams")
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<Team> saveTeam(@RequestBody TeamRequest teamRequest){
        return ResponseEntity.ok().body(teamService.save(teamRequest));
    }
}
