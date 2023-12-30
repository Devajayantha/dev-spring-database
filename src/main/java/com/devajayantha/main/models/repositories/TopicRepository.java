package com.devajayantha.main.models.repositories;

import com.devajayantha.main.models.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAll();

    Topic saveAndFlush(Topic topic);
}
