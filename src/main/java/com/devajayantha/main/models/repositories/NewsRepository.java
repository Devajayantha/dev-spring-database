package com.devajayantha.main.models.repositories;


import com.devajayantha.main.models.entities.News;
import com.devajayantha.main.models.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  NewsRepository extends JpaRepository<News, Long> {
    List<News> findAll();

    News saveAndFlush(News news);
}
