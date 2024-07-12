package com.devajayantha.main.models.dtos;

import jakarta.validation.constraints.NotBlank;

public class TopicDto {
    @NotBlank(message = "Title is required")
    private String titleTopic;

    private boolean isActive = false;

    public String getTitleTopic() {
        return titleTopic;
    }

    public void setTitleTopic(String titleTopic) {
        this.titleTopic = titleTopic;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
