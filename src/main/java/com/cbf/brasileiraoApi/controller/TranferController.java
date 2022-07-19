package com.cbf.brasileiraoApi.controller;

import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import com.cbf.brasileiraoApi.request.TransferRequest;
import com.cbf.brasileiraoApi.dto.TransferResponseDTO;
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
@RequestMapping("transfers")
@Tag(name = OpenApiConstants.TRANSFER, description = "information about transfers")
public class TranferController {
    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferResponseDTO> newTranfer(@RequestBody TransferRequest transferRequest){
        TransferResponseDTO transfer = transferService.newTransfer(transferRequest);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/id")
                        .buildAndExpand(transfer.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(transfer);
    }

    @GetMapping
    public ResponseEntity<List<TransferResponseDTO>> findAll(){
        return ResponseEntity.ok().body(transferService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> findById(@PathVariable String id){
        return ResponseEntity.ok().body(transferService.findById(id));
    }
}
