package com.devajayantha.main.services;

import com.devajayantha.main.models.dtos.NewsDto;
import com.devajayantha.main.models.entities.News;
import com.devajayantha.main.models.entities.Topic;
import com.devajayantha.main.models.repositories.NewsRepository;
import com.devajayantha.main.models.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    protected NewsRepository newsRepository;

    @Autowired
    protected  TopicRepository topicRepository;

    public List<News> findAllNews() {
        return newsRepository.findAll();
    }

    public News createNews(NewsDto newsDto) {
        Optional<Topic> topic = topicRepository.findById(newsDto.getTopicId());

        if (!topic.isPresent()) {
            throw new RuntimeException("Topic not found with id: " + newsDto.getTopicId());
        }

        News news = new News(newsDto.getTitle(), newsDto.getContent(), topic.get());

        return newsRepository.saveAndFlush(news);
    }
}