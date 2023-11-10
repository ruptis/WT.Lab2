package by.bsuir.wtlab2.model;

public class Topic {
    private int id;
    private String name;
    private int questionsCount;

    public Topic(int id, String name, int questionsCount) {
        this.id = id;
        this.name = name;
        this.questionsCount = questionsCount;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
