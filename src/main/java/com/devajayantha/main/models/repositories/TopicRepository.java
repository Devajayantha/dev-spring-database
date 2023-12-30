package com.devajayantha.main.models.repositories;

import com.devajayantha.main.models.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAll();

    Topic saveAndFlush(Topic topic);

    Optional<Topic> findById(Long id);
}
