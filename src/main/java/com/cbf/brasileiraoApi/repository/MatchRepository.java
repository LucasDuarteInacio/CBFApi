package com.cbf.brasileiraoApi.repository;

import com.cbf.brasileiraoApi.entity.Match;
import com.cbf.brasileiraoApi.entity.Transfer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends MongoRepository<Match, String > {
}
