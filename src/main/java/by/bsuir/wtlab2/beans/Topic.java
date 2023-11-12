package by.bsuir.wtlab2.beans;

import lombok.Getter;

@Getter
public class Topic {
    private int id;
    private String name;
    private int questionsCount;

    public Topic(int id, String name, int questionsCount) {
        this.id = id;
        this.name = name;
        this.questionsCount = questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
