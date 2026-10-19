package com.example.bitxchange.repository;

import com.example.bitxchange.model.AssetSnapshot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetSnapshotRepository extends MongoRepository<AssetSnapshot, String>
{
    AssetSnapshot findTopByOrderByDateDesc();
}
