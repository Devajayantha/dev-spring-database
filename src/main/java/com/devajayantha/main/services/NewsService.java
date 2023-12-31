package com.devajayantha.main.services;

import com.devajayantha.main.models.entities.News;
import com.devajayantha.main.models.entities.Topic;
import com.devajayantha.main.models.repositories.NewsRepository;
import com.devajayantha.main.models.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    protected NewsRepository newsRepository;

    public List<News> findAllNews() {
        return newsRepository.findAll();
    }
}
