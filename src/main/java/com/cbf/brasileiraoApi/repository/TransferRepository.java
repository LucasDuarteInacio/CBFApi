package com.cbf.brasileiraoApi.repository;

import com.cbf.brasileiraoApi.entity.Transfer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends MongoRepository<Transfer, String> {

    List<Transfer> findAllByDeletedFalse();
}
