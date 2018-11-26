package ua.pp.darknsoft.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPasswordUtils {
    // Encrypt Password with BCryptPasswordEncoder
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "admin"; //$2a$10$UMArHQ.Iiy3GUMxZh6Fg7.gtApfENtgYDM6TQViAObopAjSqYtmSO
        String encrytedPassword = encryptPassword(password);

        System.out.println("Encryted Password: " + encrytedPassword);

    }
}
