package by.bsuir.wtlab2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private int id;
    private String text;
    private User author;
    private Question question;
    private int reputation;
    private String answerTime;
}
