package by.bsuir.wtlab2.beans;

import lombok.Getter;

@Getter
public class Question {
    private int id;
    private String title;
    private String text;
    private int viewsCount;
    private int answersCount;
    private String author;
    private String topic;

    public Question(int id, String title, String text, int viewsCount, int answersCount, String author, String topic) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.viewsCount = viewsCount;
        this.answersCount = answersCount;
        this.author = author;
        this.topic = topic;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public void setAnswersCount(int answersCount) {
        this.answersCount = answersCount;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
