package by.bsuir.wtlab2.entity;

import by.bsuir.wtlab2.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static by.bsuir.wtlab2.constants.Role.USER;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    private int id;
    private String email;
    private String username;
    private String password;
    private Role role = USER;
    private Ban ban;

    private int reputation;
}
