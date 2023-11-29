package by.bsuir.wtlab2.service;

public interface PasswordHasher {
    String hash(String password);
    boolean check(String password, String hash);
}
