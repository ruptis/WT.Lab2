package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.UserDetails;
import by.bsuir.wtlab2.exception.AuthenticationException;
import by.bsuir.wtlab2.exception.RegistrationException;

public interface AuthenticationService {
    UserDetails authenticate(String username, String password) throws AuthenticationException;
    void register(String email, String username, String password) throws RegistrationException;
}
