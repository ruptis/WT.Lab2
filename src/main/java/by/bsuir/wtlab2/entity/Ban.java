package by.bsuir.wtlab2.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Ban {
    private int id;
    private int userId;
    private String reason;
    private LocalDateTime expiredTime;
}
