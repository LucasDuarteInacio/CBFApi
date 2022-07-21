package com.cbf.brasileiraoApi.repository;

import com.cbf.brasileiraoApi.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    List<Player> findAllByTeamId(String teamId);

    List<Player> findAllByDeletedFalse();

    Player findByIdAndDeletedIsTrue(String id);


}
