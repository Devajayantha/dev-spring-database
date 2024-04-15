package com.devajayantha.main.controllers.api;

import com.devajayantha.main.config.ResponseData;
import com.devajayantha.main.models.dtos.NewsDto;
import com.devajayantha.main.models.entities.News;
import com.devajayantha.main.services.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    @Autowired
    protected NewsService newsService;

    @GetMapping
    public ResponseData getAllNews() {
        List<News> news = newsService.findAllNews();

        return new ResponseData("success", HttpStatus.OK, news);
    }

    @PostMapping
    public ResponseData createNews(@Valid @RequestBody NewsDto newsDto, Errors errors) {
        ResponseData errorMessages = ResponseData.getResponseData(errors);
        if (errorMessages != null) return errorMessages;

        News savedNews = newsService.createNews(newsDto);

        return new ResponseData("success created", HttpStatus.CREATED, savedNews);
    }

    @GetMapping("/{id}")
    public ResponseData getNews(@PathVariable("id") Long id) throws Exception {
        Optional<News> news = Optional.ofNullable(newsService.findNewsById(id));

        return news.map(value -> new ResponseData("Success", HttpStatus.OK, news))
                .orElseThrow(() -> new Exception("News not found"));
    }

    @PatchMapping("/{id}")
    public ResponseData updateNews(@PathVariable("id") Long id, @Valid @RequestBody NewsDto newsDto, Errors errors) throws Exception {
        ResponseData errorMessages = ResponseData.getResponseData(errors);
        if (errorMessages != null) return errorMessages;

        News news = newsService.findNewsById(id);

        if (news == null) throw new Exception("News not found");

        News updatedNews = newsService.updateNews(news, newsDto);

        return new ResponseData("Success Updated", HttpStatus.OK, updatedNews);
    }

    @DeleteMapping("/{id}")
    public ResponseData deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);

        return new ResponseData("Success Deleted", HttpStatus.OK, null);
    }

}
