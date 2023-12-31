package com.devajayantha.main.services;

import com.devajayantha.main.models.dtos.TopicDto;
import com.devajayantha.main.models.entities.Topic;
import com.devajayantha.main.models.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
