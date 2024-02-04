package com.devajayantha.main.controllers.api;

import com.devajayantha.main.config.ResponseHandler;
import com.devajayantha.main.models.dtos.NewsDto;
import com.devajayantha.main.models.entities.News;
import com.devajayantha.main.models.entities.Topic;
import com.devajayantha.main.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    @Autowired
    protected NewsService newsService;

    @GetMapping
    public ResponseEntity<Object> getAllNews() {
        List<News> news = newsService.findAllNews();

        return ResponseHandler.response("success", HttpStatus.OK, news);
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody NewsDto newsDto) {
        News savedNews = newsService.createNews(newsDto);

        return new ResponseEntity<>(savedNews, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNews(@PathVariable("id") Long id) {
        Optional<News> news = Optional.ofNullable(newsService.findNewsById(id));

        return news.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable("id") Long id, @RequestBody NewsDto newsDto) {
        News news = newsService.findNewsById(id);

        if (news == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        News updatedNews = newsService.updateNews(news, newsDto);

        return new ResponseEntity<>(updatedNews, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
