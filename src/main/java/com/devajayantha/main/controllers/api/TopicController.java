package com.devajayantha.main.controllers.api;

import com.devajayantha.main.config.ResponseData;
import com.devajayantha.main.models.dtos.TopicDto;
import com.devajayantha.main.models.entities.Topic;
import com.devajayantha.main.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {

    @Autowired
    protected TopicService topicService;

    @GetMapping
    public ResponseData getAllTopics() {
        List<Topic> topics = topicService.findAllTopics();

        return new ResponseData("Success", HttpStatus.OK, topics);
    }

    @PostMapping
    public ResponseData createTopic(@Valid @RequestBody TopicDto topicDto, Errors errors) {
        ResponseData errorMessages = ResponseData.getResponseData(errors);
        if (errorMessages != null) return errorMessages;

        Topic savedTopic = topicService.createTopic(topicDto);

        return new ResponseData("Success Created", HttpStatus.OK, savedTopic);
    }

    @GetMapping("/{id}")
    public ResponseData getTopic(@PathVariable("id") Long id) throws Exception {
        Optional<Topic> topic = Optional.ofNullable(topicService.findTopicById(id));

        return topic.map(value -> new ResponseData("Success", HttpStatus.OK, topic))
                .orElseThrow(() -> new Exception("Topic not found"));
    }

    @PatchMapping("/{id}")
    public ResponseData updateTopic(@PathVariable("id") Long id, @Valid @RequestBody TopicDto topicDto, Errors errors) throws Exception {
        ResponseData errorMessages = ResponseData.getResponseData(errors);
        if (errorMessages != null) return errorMessages;

        Optional<Topic> topic = topicService.updateTopic(topicDto, id);

        return topic.map(value -> new ResponseData("Success Updated", HttpStatus.OK, topic))
                .orElseThrow(() -> new Exception("Topic not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseData deleteTopic(@PathVariable("id") Long id) {
        topicService.deleteTopic(id);

        return new ResponseData("Success", HttpStatus.OK, null);
    }
}
