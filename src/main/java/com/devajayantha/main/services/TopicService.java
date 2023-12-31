package com.devajayantha.main.services;

import com.devajayantha.main.models.dtos.TopicDto;
import com.devajayantha.main.models.entities.Topic;
import com.devajayantha.main.models.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    protected TopicRepository topicRepository;

    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    public Topic createItem(TopicDto TopicDto) {
        Topic topic = new Topic(TopicDto.getTitleTopic(), TopicDto.isActive());

        return topicRepository.saveAndFlush(topic);
    }

    public Topic findTopicById(Long id) {
        return topicRepository.findById(id).orElse(null);
    }

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
}
