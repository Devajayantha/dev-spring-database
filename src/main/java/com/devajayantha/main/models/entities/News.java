package com.devajayantha.main.models.entities;

import jakarta.persistence.*;

@Table(name = "news")
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public News(){}

    public News(String title, String content, Topic topic){
        this.title = title;
        this.content = content;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public Topic getTopic(){
        return topic;
    }

    public void setTopic(Topic topic){
        this.topic = topic;
    }
}
