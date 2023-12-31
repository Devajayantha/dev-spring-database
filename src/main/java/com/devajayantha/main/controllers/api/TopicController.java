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
    public List<Topic> getAllTopics() {
        return topicService.findAllTopics();
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(@Validated @RequestBody TopicDto topicDto) {
        Topic savedTopic = topicService.createItem(topicDto);
        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable("id") Long id) {
        Optional<Topic> topic = Optional.ofNullable(topicService.findTopicById(id));

        return topic.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable("id") Long id, @Validated @RequestBody TopicDto topicDto) {
        try {
            Optional<Topic> topic = topicService.updateTopic(topicDto, id);

            return topic.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseThrow(() -> new Exception("Topic not found"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
