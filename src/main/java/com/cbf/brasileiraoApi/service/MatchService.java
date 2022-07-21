package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.entity.Event;
import com.cbf.brasileiraoApi.entity.Match;
import com.cbf.brasileiraoApi.entity.enums.EventTypeEnum;
import com.cbf.brasileiraoApi.exception.BadRequestException;
import com.cbf.brasileiraoApi.exception.NotFoundException;
import com.cbf.brasileiraoApi.mapper.MatchMapper;
import com.cbf.brasileiraoApi.mapper.PlayerMapper;
import com.cbf.brasileiraoApi.mapper.TournamentMapper;
import com.cbf.brasileiraoApi.repository.MatchRepository;
import com.cbf.brasileiraoApi.request.EventRequest;
import com.cbf.brasileiraoApi.request.MatchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Match newMatch(MatchRequest matchRequest) {
        Match match = matchMapper.toDomain(
                matchRequest,
                teamService.findById(matchRequest.getHomeTeamId()),
                teamService.findById(matchRequest.getVisitingTeamId()),
                tournamentMapper.toDomain(tournamentService.findById(matchRequest.getTournamentId())),
                Boolean.FALSE
        );
        return save(match);
    }

    private Match save(Match match) {
        return matchRepository.save(match);
    }

    public Match findById(String id) {
        return matchRepository.findById(id).orElseThrow(NotFoundException::matchNotFound);
    }

    public List<Match> findAllByTournamentId(String tournamentId) {
        return matchRepository.findAllByTournamentIdAndDeletedIsFalse(tournamentId);
    }

    public void delete(String id) {
        Match match = findById(id);
        match.setDeleted(Boolean.TRUE);
        save(match);
    }

    public Event newEvent(String id, EventTypeEnum typeEvent, EventRequest eventRequest) {
        Match match = findById(id);
        Event event = getTypeEvent(eventRequest, typeEvent);
        match.getEvents().add(event);
        save(match);
        return event;
    }

    private Event getTypeEvent(EventRequest eventRequest, EventTypeEnum typeEvent) {
        Event event;
        switch (typeEvent) {
            case START:
                event = Event.builder()
                        .minutes("00:00")
                        .eventType(String.valueOf(EventTypeEnum.START))
                        .build();
                break;
            case BREAK:
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .eventType(String.valueOf(EventTypeEnum.BREAK))
                        .build();
                break;
            case END:
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .eventType(String.valueOf(EventTypeEnum.END))
                        .build();
                break;
            case GOAL:
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .eventType(String.valueOf(EventTypeEnum.GOAL))
                        .build();
                break;
            case YELLOWCARD:
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .eventType(String.valueOf(EventTypeEnum.YELLOWCARD))
                        .build();
                break;
            case REDCARD:
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .eventType(String.valueOf(EventTypeEnum.REDCARD))
                        .build();
                break;
            case ADDITION:
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .addition(eventRequest.getAddition())
                        .eventType(String.valueOf(EventTypeEnum.ADDITION))
                        .build();
                break;
            case SUBSTITUTION:
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .playerOut(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerOutId())))
                        .eventType(String.valueOf(EventTypeEnum.SUBSTITUTION))
                        .build();
                break;
            case FOUL:
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .eventType(String.valueOf(EventTypeEnum.FOUL))
                        .build();
                break;
            case PENALTY:
                event = Event.builder()
                        .minutes(eventRequest.getMinutes())
                        .player(playerMapper.toDomain(playerService.findById(eventRequest.getPlayerId())))
                        .eventType(String.valueOf(EventTypeEnum.PENALTY))
                        .build();
                break;
            default:
                throw new BadRequestException(BadRequestException.eventTypeBadRequest().getIssue());
        }
        return event;
    }
}
