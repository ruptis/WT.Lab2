package by.bsuir.wtlab2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private long id;
    private String text;
    private User author;
    private Question question;
    private int reputation;
    private LocalDateTime answerTime;
}
