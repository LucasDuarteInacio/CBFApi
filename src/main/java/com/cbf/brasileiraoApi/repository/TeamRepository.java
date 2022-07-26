package com.cbf.brasileiraoApi.repository;

import com.cbf.brasileiraoApi.entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
    List<Team> findAllByIdInAndDeletedFalse(List<String> listId);

    List<Team> findAllByDeletedFalse();

}
