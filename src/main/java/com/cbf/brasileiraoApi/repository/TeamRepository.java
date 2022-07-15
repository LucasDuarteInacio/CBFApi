package com.cbf.brasileiraoApi.repository;

import com.cbf.brasileiraoApi.entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, String > {
}
