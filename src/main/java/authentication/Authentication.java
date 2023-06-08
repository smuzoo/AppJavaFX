package authentication;

import Database.Database;
import utils.readers.Reader;
import validators.Errors;
import validators.fields.NameValidator;
import validators.fields.NotEqualsValidator;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

/**
 * The type Authentication.
 */
public class Authentication {


    final private static String NAME_TABLE = "users";


    public static Errors register(String login, String password) {
        Database db = Database.getInstance();
        if (login.isBlank() || password.isBlank()) return Errors.EMPTYFIELD;
        else if (db.isExistInDB(NAME_TABLE, "login", login)) {
            System.err.println("Данное имя пользователя уже существует");
            return Errors.USEDLOGIN;
        } else {
            String pepper = "hAV~2zRmv#";
            String salt = getRandomString();
            try {
                MessageDigest md = MessageDigest.getInstance("MD2");
                byte[] hash = md.digest(
                        (pepper + password + salt).getBytes(StandardCharsets.UTF_8));
                db.addUserToDB(NAME_TABLE, login, salt, Arrays.toString(hash));
                User.setLogin(login);
                User.setPassword(password);
                System.out.println("Вы были успешно зарегистрированы");
                return Errors.NOTHAVEERRORS;
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }

        }

        return Errors.NOTHAVEERRORS;
    }

    public static boolean isLogin(String login, String password) {
        Database db = Database.getInstance();
        if (!db.isExistInDB(NAME_TABLE, "login", login)) {
            System.err.println("Данного имя пользователя не существует");
            return false;
        } else {
            String pepper = "hAV~2zRmv#";
            String salt = db.getFieldByField(NAME_TABLE, "login", login, "salt");
            try {
                MessageDigest md = MessageDigest.getInstance("MD2");
                byte[] hash = md.digest(
                        (pepper + password + salt).getBytes(StandardCharsets.UTF_8));
                String hashUser = Arrays.toString(hash);
                String hashDB = db.getFieldByField(NAME_TABLE, "login", login, "hash");
                if (hashUser.equals(hashDB)) {
                    User.setLogin(login);
                    User.setPassword(password);
                    System.out.println("Вы успешно вошли в систему");
                    return true;
                } else {
                    System.err.println("Вы ввели неправильный пароль для пользователя " + login);
                    return false;
                }
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    private static String getRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@?/%&*";
        int length = 10;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(characters.length());
            char c = characters.charAt(index);
            sb.append(c);
        }
        return sb.toString();
    }
}
