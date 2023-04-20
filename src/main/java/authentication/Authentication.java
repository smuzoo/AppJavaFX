package authentication;

import Database.Database;
import utils.readers.Reader;
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

    private static Reader reader;

    final private static String NAME_TABLE = "users";

    /**
     * Auth.
     */
    public static void auth() {
        String action;
        do{
            System.out.println("Введите 1 если хотите зарегистрироваться, 2 если войти, 3 если войти гостем, 4 если выйти");
            action = reader.getNewLine();
        }while (!(new NotEqualsValidator(action, "1", "2", "3", "4").isValid()));
        if(action.equals("4")){
            System.exit(0);
        } else if (!action.equals("3")) {
            String login = get("имя пользователя");
            String password = get("пароль");
            switch (action){
                case "1" -> register(login, password);
                case "2" -> login(login, password);
            }
        }

    }


    private static void register(String login, String password) {
        Database db = Database.getInstance();
        if(db.isExistInDB(NAME_TABLE, "login", login)){
            System.err.println("Данное имя пользователя уже существует");
            auth();
        }else{
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
            }catch (NoSuchAlgorithmException ex){
                ex.printStackTrace();
            }

        }


    }

    private static void login(String login, String password) {
        Database db = Database.getInstance();
        if (!db.isExistInDB(NAME_TABLE, "login", login)) {
            System.err.println("Данного имя пользователя не существует");
            auth();
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
                } else {
                    System.err.println("Вы ввели неправильный пароль для пользователя " + login);
                    auth();
                }
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        }
    }


    private static String get(String field){
        String answer;
        do {
            System.out.println("Введите " + field);
            answer = reader.getNewLine();
        } while (!(new NameValidator(answer).isValid()));
        return answer;
    }

    private static String getRandomString(){
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

    /**
     * Sets reader.
     *
     * @param reader the reader
     */
    public static void setReader(Reader reader) {
        Authentication.reader = reader;
    }
}
