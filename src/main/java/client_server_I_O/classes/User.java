package client_server_I_O.classes;

import java.io.Serializable;

public class User implements Serializable {
    static final long serialVersionUID = 4566843898265039363L;

    private String login;
    private String password;
    private Snake snake;

    public User() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
