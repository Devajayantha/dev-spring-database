package com.devajayantha.main.controllers.api;

import com.devajayantha.main.models.dtos.TopicDto;
import com.devajayantha.main.models.entities.Topic;
import com.devajayantha.main.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Topic> show(@PathVariable("id") Long id) {
        Optional<Topic> topic = Optional.ofNullable(topicService.findById(id));

        return topic.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
