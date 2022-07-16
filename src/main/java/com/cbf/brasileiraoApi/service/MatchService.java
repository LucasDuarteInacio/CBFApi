package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.EventRequest;
import com.cbf.brasileiraoApi.dto.MatchRequest;
import com.cbf.brasileiraoApi.dto.PlayerResponseDTO;
import com.cbf.brasileiraoApi.entity.*;
import com.cbf.brasileiraoApi.entity.enums.EventTypeEnum;
import com.cbf.brasileiraoApi.entity.enums.InfractionTypeEnum;
import com.cbf.brasileiraoApi.entity.enums.TournamentTypeEnum;
import com.cbf.brasileiraoApi.mapper.MatchMapper;
import com.cbf.brasileiraoApi.mapper.PlayerMapper;
import com.cbf.brasileiraoApi.mapper.TournamentMapper;
import com.cbf.brasileiraoApi.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final TournamentMapper tournamentMapper;
    private final TeamService teamService;
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;
    private final TournamentService tournamentService;

    public Match newMatch(MatchRequest matchRequest){
        Match match = matchMapper.toDomain(
                matchRequest,
                teamService.findById(matchRequest.getHomeTeamId()),
                teamService.findById(matchRequest.getVisitingTeamId()),
                tournamentMapper.toDomain(tournamentService.findById(matchRequest.getTournamentId()))
        );
      return save(match);
    }

    private Match save(Match match){
        return matchRepository.save(match);
    }

    public Match findById(String id){
        return matchRepository.findById(id).get();
    }

    public Event newEvent(String id, String typeEvent, EventRequest eventRequest){
        Match match = findById(id);
        Event event = getTypeEvent(eventRequest,typeEvent);
        match.getEvents().add(event);
       save(match);
        return event;
    }

    private Event getTypeEvent(EventRequest eventRequest, String typeEvent){
        Event event;
        switch (typeEvent){
            case "start":
                event = Event.builder()
                        .minutes("00:00")
                        .eventType(String.valueOf(EventTypeEnum.START))
                        .build();
                break;
            case "break":
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .eventType(String.valueOf(EventTypeEnum.BREAK))
                        .build();
                break;
            case "end":
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .eventType(String.valueOf(EventTypeEnum.END))
                        .build();
                break;
            case "goal":
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .eventType(String.valueOf(EventTypeEnum.GOAL))
                        .build();
                break;
            case "yellowcard":
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .eventType(String.valueOf(EventTypeEnum.YELLOWCARD))
                        .build();
                break;
            case "redcard":
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .eventType(String.valueOf(EventTypeEnum.REDCARD))
                        .build();
                break;
            case "addition":
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .addition(eventRequest.getAddition())
                        .eventType(String.valueOf(EventTypeEnum.ADDITION))
                        .build();
                break;
            case "substution":
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .playerOut(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerOutId())))
                        .eventType(String.valueOf(EventTypeEnum.SUBSTITUTION))
                        .build();
                break;
            case "foul":
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .infractionType(String.valueOf(InfractionTypeEnum.FOUL))
                        .eventType(String.valueOf(EventTypeEnum.INFRACTION))
                        .build();
                break;
            case "penalty":
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .infractionType(String.valueOf(InfractionTypeEnum.PENALTY))
                        .eventType(String.valueOf(EventTypeEnum.INFRACTION))
                        .build();
                break;
            default:
                throw new RuntimeException("Tipo de evento invalido");
        }
        return event;
    }
}
