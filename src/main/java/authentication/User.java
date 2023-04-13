package authentication;

public class User {

    private static String login;

    private static String password;

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        User.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static boolean isLogin(){
        return login != null && password != null;
    }
}
