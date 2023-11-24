package by.bsuir.wtlab2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private int id;
    private String title;
    private String text;
    private int viewsCount;
    private int answersCount;
    private User author;
    private Topic topic;
    private String askTime;
    private String lastUpdateTime;
}
