package by.bsuir.wtlab2.entity;

import by.bsuir.wtlab2.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static by.bsuir.wtlab2.constants.Role.USER;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    private long id;
    private String email;
    private String username;
    private String password;
    private Role role = USER;

    private int reputation;
    private int questionsCount;
    private int answersCount;
    private LocalDateTime registrationDate;
}
