package com.devajayantha.main.controllers.api;

import com.devajayantha.main.models.dtos.TopicDto;
import com.devajayantha.main.models.entities.Topic;
import com.devajayantha.main.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {

    @Autowired
    protected TopicService topicService;

    @GetMapping
    public List<Topic> index() {
        return topicService.findAll();
    }

    @PostMapping
    public ResponseEntity<Topic> store(@Validated @RequestBody TopicDto topicDto) {
        Topic savedTopic = topicService.save(topicDto);
        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }
}
