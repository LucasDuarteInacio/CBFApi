package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.TransferRequest;
import com.cbf.brasileiraoApi.dto.TransferResponseDTO;
import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Transfer;
import com.cbf.brasileiraoApi.mapper.TransferMapper;
import com.cbf.brasileiraoApi.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;
    private final TeamService  teamService;
    private final PlayerService  playerService;

    public TransferResponseDTO newTransfer(TransferRequest transferRequest){
        Player player = playerService.findById(transferRequest.getPlayerId());
        Team destinationTeam = teamService.findById(transferRequest.getDestinationTeamId());

        playerService.changePlayerTeam(player,destinationTeam);
        Transfer transfer = Transfer.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .player(player)
                .originalTeam(teamService.findById(transferRequest.getOriginalTeamId()))
                .destinationTeam(destinationTeam)
                .date(transferRequest.getDate())
                .tranferValue(transferRequest.getTranferValue())
                .build();

        return transferMapper.toReponseDTOWithoutTeam(transferRepository.save(transfer));
    }

    public List<TransferResponseDTO> findAll() {
        return transferMapper.toReponseDTO( transferRepository.findAll());
    }

    public TransferResponseDTO findById(String id) {
        return transferMapper.toReponseDTOWithoutTeam(transferRepository.findById(id).get());
    }



}
