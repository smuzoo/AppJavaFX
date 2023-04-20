package authentication;

/**
 * The type User.
 */
public class User {

    private static String login;

    private static String password;

    /**
     * Gets login.
     *
     * @return the login
     */
    public static String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public static void setLogin(String login) {
        User.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public static void setPassword(String password) {
        User.password = password;
    }

    /**
     * Is login boolean.
     *
     * @return the boolean
     */
    public static boolean isLogin(){
        return login != null && password != null;
    }
}
