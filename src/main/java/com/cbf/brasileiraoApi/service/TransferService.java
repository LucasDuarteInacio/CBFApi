package com.cbf.brasileiraoApi.service;

import com.cbf.brasileiraoApi.dto.TransferResponseDTO;
import com.cbf.brasileiraoApi.entity.Player;
import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Transfer;
import com.cbf.brasileiraoApi.exception.BadRequestException;
import com.cbf.brasileiraoApi.exception.NotFoundException;
import com.cbf.brasileiraoApi.mapper.PlayerMapper;
import com.cbf.brasileiraoApi.mapper.TransferMapper;
import com.cbf.brasileiraoApi.repository.TransferRepository;
import com.cbf.brasileiraoApi.request.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;
    private final PlayerMapper playerMapper;
    private final TeamService teamService;
    private final PlayerService playerService;

    public TransferResponseDTO newTransfer(TransferRequest transferRequest) {
        if (playerService.isPlayerDeleted(transferRequest.getPlayerId())) {
            throw new BadRequestException(BadRequestException.playerBadRequest().getIssue());
        }
        Player player = playerMapper.toDomain(playerService.findById(transferRequest.getPlayerId()));
        Team destinationTeam = teamService.findById(transferRequest.getDestinationTeamId());
        Team originalTeam = teamService.findById(player.getTeam().getId());

        Transfer transfer = Transfer.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .player(player)
                .originalTeam(originalTeam)
                .destinationTeam(destinationTeam)
                .date(transferRequest.getDate())
                .tranferValue(transferRequest.getTranferValue())
                .deleted(Boolean.FALSE)
                .build();
        playerService.changePlayerTeam(player, destinationTeam);

        return transferMapper.toReponseDTOWithoutTeam(transferRepository.save(transfer));
    }

    public List<TransferResponseDTO> findAll() {
        return transferMapper.toReponseDTO(transferRepository.findAllByDeletedFalse());
    }

    public TransferResponseDTO findById(String id) {
        return transferMapper.toReponseDTOWithoutTeam(transferRepository.findById(id).orElseThrow(NotFoundException::transferNotFound));
    }

    public void delete(String id) {
        Transfer transfer = transferMapper.toDomain(findById(id));
        if (playerService.isPlayerDeleted(transfer.getPlayer().getId())) {
            throw new BadRequestException(BadRequestException.playerBadRequest().getIssue());
        }
        transfer.setDeleted(Boolean.TRUE);
        playerService.changePlayerTeam(transfer.getPlayer(), transfer.getOriginalTeam());
        transferRepository.save(transfer);
    }


}
