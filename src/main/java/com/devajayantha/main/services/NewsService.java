package com.devajayantha.main.services;

import com.devajayantha.main.models.dtos.NewsDto;
import com.devajayantha.main.models.entities.News;
import com.devajayantha.main.models.entities.Topic;
import com.devajayantha.main.models.repositories.NewsRepository;
import com.devajayantha.main.models.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    protected NewsRepository newsRepository;

    @Autowired
    protected  TopicRepository topicRepository;

    public Page<News> findAllNews(int page, int size) {
        Pageable pageable = PageRequest.of(
                page, size,
                Sort.by(Sort.Direction.ASC, "title")
        );

        return newsRepository.findAll(pageable);
    }

    @Transactional
    public News createNews(NewsDto newsDto) {
        Optional<Topic> topic = topicRepository.findById(newsDto.getTopicId());

        if (!topic.isPresent()) {
            throw new RuntimeException("Topic not found with id: " + newsDto.getTopicId());
        }

        News news = new News(newsDto.getTitle(), newsDto.getContent(), topic.get());

        return newsRepository.saveAndFlush(news);
    }

    public News findNewsById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Transactional
    public News updateNews(News news, NewsDto newsDto) {
        Optional<Topic> topic = topicRepository.findById(newsDto.getTopicId());

        if (!topic.isPresent()) {
            throw new RuntimeException("Topic not found with id: " + newsDto.getTopicId());
        }

        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setTopic(topic.get());

        return newsRepository.saveAndFlush(news);
    }

    @Transactional
    public void deleteNews(Long id) {
        Optional<News> news = newsRepository.findById(id);

        if (news.isPresent()) {
            newsRepository.delete(news.get());
        } else {
            throw new EntityNotFoundException("Topic not found with id: " + id);
        }
    }
}
