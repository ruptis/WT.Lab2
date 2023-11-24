package by.bsuir.wtlab2.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ban {
    private String reason;
    private String startTime;
    private String endTime;
}
