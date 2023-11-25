package by.bsuir.wtlab2.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    private int id;
    private String name;
    private User author;
    private int questionsCount;
    private LocalDateTime creationTime;
}
