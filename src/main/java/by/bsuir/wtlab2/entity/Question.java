package by.bsuir.wtlab2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private int id;
    private String title;
    private String text;
    private int viewsCount;
    private int answersCount;
    private String author;
    private String topic;
}
