package com.devajayantha.main.services;

import com.devajayantha.main.models.dtos.TopicDto;
import com.devajayantha.main.models.entities.Topic;
import com.devajayantha.main.models.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    protected TopicRepository topicRepository;

    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    @Transactional
    public Topic createTopic(TopicDto TopicDto) {
        Topic topic = new Topic(TopicDto.getTitleTopic(), TopicDto.isActive());

        return topicRepository.saveAndFlush(topic);
    }

    public Topic findTopicById(Long id) {
        return topicRepository.findById(id).orElse(null);
    }

    @Transactional
    public Optional<Topic> updateTopic(TopicDto topicDto, Long id) {
        Optional<Topic> topic = topicRepository.findById(id);

        if (topic.isPresent()) {
            return topic.map(model -> {
                model.setTitle(topicDto.getTitleTopic());
                model.setIsActive(topicDto.isActive());

                return topicRepository.saveAndFlush(model);
            });
        }

        return Optional.empty();
    }

    @Transactional
    public void deleteTopic(Long id) {
        Optional<Topic> topic = topicRepository.findById(id);

        if (topic.isPresent()) {
            topicRepository.delete(topic.get());
        } else {
            throw new EntityNotFoundException("Topic not found with id: " + id);
        }
    }

}
