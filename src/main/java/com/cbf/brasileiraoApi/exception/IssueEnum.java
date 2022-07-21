package com.cbf.brasileiraoApi.exception;

import lombok.Getter;


@Getter
public enum IssueEnum {

    TOURNAMENT_NOT_FOUND("Torneio não encontrado."),
    TEAM_NOT_FOUND("Time não encontrado."),
    TRANSFER_NOT_FOUND("Transferência  não encontrado."),
    MATCH_NOT_FOUND("Partida não encontrada."),
    PLAYER_NOT_FOUND("Jogador não encontrado."),
    TOURNAMENT_BAD_REQUEST("Torneio inválido."),
    PLAYER_BAD_REQUEST("Jogador inválido."),
    EVENT_TYPE_BAD_REQUEST("Tipo de evento inválido.");

    private final String message;

    IssueEnum(String message) {
        this.message = message;
    }


}