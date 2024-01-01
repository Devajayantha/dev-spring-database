package com.devajayantha.main.controllers.api;

import com.devajayantha.main.models.dtos.NewsDto;
import com.devajayantha.main.models.entities.News;
import com.devajayantha.main.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    @Autowired
    protected NewsService newsService;

    @GetMapping
    public List<News> getAllNews() {
        return newsService.findAllNews();
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody NewsDto newsDto) {
        News savedNews = newsService.createNews(newsDto);

        return new ResponseEntity<>(savedNews, HttpStatus.CREATED);
    }
}
