package com.devajayantha.main.models.dtos;

public class TopicDto {
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
