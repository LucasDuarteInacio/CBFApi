package com.cbf.brasileiraoApi.repository;

import com.cbf.brasileiraoApi.entity.Team;
import com.cbf.brasileiraoApi.entity.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends MongoRepository<Tournament, String> {
}
