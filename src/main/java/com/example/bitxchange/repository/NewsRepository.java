package com.example.bitxchange.repository;

import com.example.bitxchange.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends MongoRepository<News, String>
{
    List<News> findTop5ByOrderByPublishedAtDesc();
}

