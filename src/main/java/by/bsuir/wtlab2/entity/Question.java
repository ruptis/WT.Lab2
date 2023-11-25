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
public class Question {
    private long id;
    private String title;
    private String text;
    private int viewsCount;
    private int answersCount;
    private User author;
    private Topic topic;
    private LocalDateTime askTime;
    private LocalDateTime lastUpdateTime;
}
