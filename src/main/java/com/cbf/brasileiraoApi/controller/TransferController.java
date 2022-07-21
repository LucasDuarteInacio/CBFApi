package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import com.cbf.brasileiraoApi.dto.TransferResponseDTO;
import com.cbf.brasileiraoApi.request.TransferRequest;
import com.cbf.brasileiraoApi.service.TransferService;
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
@RequestMapping("transfers")
@Tag(name = OpenApiConstants.TRANSFER, description = "information about transfers")
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    @Operation(summary = "Register new transfer")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Register new transfer")
            }
    )
    public ResponseEntity<TransferResponseDTO> newTranfer(@RequestBody TransferRequest transferRequest) {
        TransferResponseDTO transfer = transferService.newTransfer(transferRequest);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/id")
                        .buildAndExpand(transfer.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(transfer);
    }

    @GetMapping
    @Operation(summary = "find all tranfers")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "find all players")
            }
    )
    public ResponseEntity<List<TransferResponseDTO>> findAll() {
        return ResponseEntity.ok().body(transferService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find transfer by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Find transfer by id"),
                    @ApiResponse(responseCode = "404", description = "Transfer not found")
            }
    )
    public ResponseEntity<TransferResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(transferService.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete transfer by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Delete transfer by id"),
                    @ApiResponse(responseCode = "404", description = "Transfer not found")
            }
    )
    public ResponseEntity<Void> delete(@PathVariable String id) {
        transferService.delete(id);
        return ResponseEntity.ok().build();
    }
}
