package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.service.PasswordHasher;
import org.mindrot.jbcrypt.BCrypt;

@Singleton
public class PasswordHasherImpl implements PasswordHasher {
    @Override
    public String hash(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    @Override
    public boolean check(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
