package com.cbf.brasileiraoApi.repository;

import com.cbf.brasileiraoApi.entity.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends MongoRepository<Match, String> {

    List<Match> findAllByTournamentIdAndDeletedIsFalse(String id);
}
