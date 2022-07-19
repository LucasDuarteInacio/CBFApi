package com.cbf.brasileiraoApi.exception;

public class NotFoundException extends ApiException {

    public NotFoundException(final Issue issue) {
        super(issue);
    }

    public static NotFoundException teamNotFound() {
        return new NotFoundException(new Issue(IssueEnum.TEAM_NOT_FOUND));
    }

    public static NotFoundException matchNotFound() {
        return new NotFoundException(new Issue(IssueEnum.MATCH_NOT_FOUND));
    }

    public static NotFoundException playerNotFound() {
        return new NotFoundException(new Issue(IssueEnum.PLAYER_NOT_FOUND));
    }

    public static NotFoundException transferNotFound() {
        return new NotFoundException(new Issue(IssueEnum.TRANSFER_NOT_FOUND));
    }

    public static NotFoundException tournamentNotFound() {
        return new NotFoundException(new Issue(IssueEnum.TOURNAMENT_NOT_FOUND));
    }
}
