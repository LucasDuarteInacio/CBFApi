package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import com.cbf.brasileiraoApi.request.EventRequest;
import com.cbf.brasileiraoApi.request.MatchRequest;
import com.cbf.brasileiraoApi.entity.Event;
import com.cbf.brasileiraoApi.entity.Match;
import com.cbf.brasileiraoApi.service.MatchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("matchers")
@Tag(name = OpenApiConstants.MATCH, description = "information about matches")
public class MatchController {
    private final MatchService matchService;

    @PostMapping
    public ResponseEntity<Match> newMatch(@RequestBody MatchRequest matchRequest){
        Match match = matchService.newMatch(matchRequest);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/id")
                        .buildAndExpand(match.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(match);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> findById(@PathVariable String id){
        return ResponseEntity.ok().body(matchService.findById(id));
    }

    @PostMapping("/{id}/events/{typeEvent}")
    public ResponseEntity<Event> event(@PathVariable String id,@PathVariable String typeEvent,@RequestBody EventRequest eventRequest){
        Event event = matchService.newEvent(id,typeEvent,eventRequest);
        return ResponseEntity.ok().body(event);
    }
}
