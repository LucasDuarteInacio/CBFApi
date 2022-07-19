package com.cbf.brasileiraoApi.exception;

public class BadRequestException extends ApiException {

    public BadRequestException(final Issue issue) {
        super(issue);
    }

    public static BadRequestException eventTypeBadRequest() {
        return new BadRequestException(new Issue(IssueEnum.EVENT_TYPE_BAD_REQUEST));
    }

    public static BadRequestException tournamentBadRequest() {
        return new BadRequestException(new Issue(IssueEnum.TOURNAMENT_BAD_REQUEST));
    }
}
