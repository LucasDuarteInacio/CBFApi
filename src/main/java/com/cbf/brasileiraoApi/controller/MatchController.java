package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import com.cbf.brasileiraoApi.dto.MatchRequest;
import com.cbf.brasileiraoApi.dto.TransferRequest;
import com.cbf.brasileiraoApi.dto.TransferResponseDTO;
import com.cbf.brasileiraoApi.entity.Match;
import com.cbf.brasileiraoApi.service.MatchService;
import com.cbf.brasileiraoApi.service.TransferService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("matchers")
@Tag(name = OpenApiConstants.MATCH)
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
}
