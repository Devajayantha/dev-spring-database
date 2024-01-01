package com.devajayantha.main.models.repositories;

import com.devajayantha.main.models.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAll();

    Topic saveAndFlush(Topic topic);

    Optional<Topic> findById(Long id);

    void delete(Topic topic);
}
