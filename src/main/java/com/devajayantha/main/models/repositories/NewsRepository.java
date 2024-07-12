package com.devajayantha.main.models.repositories;


import com.devajayantha.main.models.entities.News;
import com.devajayantha.main.models.entities.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAll(Pageable pageable);

    News saveAndFlush(News news);

    Optional<News> findById(Long id);

    void delete(News news);
}
