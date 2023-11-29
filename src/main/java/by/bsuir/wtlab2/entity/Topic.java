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
public class Topic {
    private long id;
    private String name;
    private User author;
    private int questionsCount;
    private LocalDateTime creationTime;
}
