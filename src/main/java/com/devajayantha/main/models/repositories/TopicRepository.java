package com.devajayantha.main.models.repositories;

import com.devajayantha.main.models.entities.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findAll(Pageable pageable);

    @Query("SELECT t FROM Topic t WHERE t.isActive = true")
    Page<Topic> findAllActiveTopics(Pageable pageable);

    Topic saveAndFlush(Topic topic);

    Optional<Topic> findById(Long id);

    void delete(Topic topic);
}
