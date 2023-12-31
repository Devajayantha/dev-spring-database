package com.devajayantha.main.controllers.api;

import com.devajayantha.main.models.entities.News;
import com.devajayantha.main.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
