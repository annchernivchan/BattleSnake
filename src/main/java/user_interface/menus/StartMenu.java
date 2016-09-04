package user_interface.menus;

import client_server_I_O.Client;
import client_server_I_O.classes.User;
import javafx.application.Platform;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import main.Main;
import main.SnakePane;
import messages.Messenger;
import user_interface.Component;
import user_interface.ComponentBuilder;
import user_interface.account.MainMenu;
import user_interface.account.battlefield.menu.SnakesPane;
import user_interface.animation.TransitionAnimation;

import static messages.MessageType.*;

public class StartMenu {

    private static final double BUTTON_OPACITY = 0.5;
    private static final Color BUTTON_COLOR = Color.web("0x195519");
    private static final Color TEXT_COLOR = Color.web("0x58B858");

    public static final SubMenu mainMenu = getMainMenu();
    public static final SubMenu authorizationMenu = getAuthorizationMenu();
    public static final SubMenu registrationMenu = getRegistrationMenu();

    public static SubMenu getMainMenu() {
        Region authorization = ComponentBuilder.getButton("АВТОРИЗАЦІЯ", BUTTON_OPACITY, TEXT_COLOR, BUTTON_COLOR);
        Region registration = ComponentBuilder.getButton("РЕЄСТРАЦІЯ", BUTTON_OPACITY, TEXT_COLOR, BUTTON_COLOR);
        Region exitGame = ComponentBuilder.getButton("ВИХІД", BUTTON_OPACITY, TEXT_COLOR, BUTTON_COLOR);

        authorization.setOnMouseClicked(event -> MenuBox.setSubMenu(authorizationMenu));
        registration.setOnMouseClicked(event -> MenuBox.setSubMenu(registrationMenu));
        exitGame.setOnMouseClicked(event -> System.exit(0));

        return new SubMenu(authorization, registration, exitGame);
    }

    public static SubMenu getAuthorizationMenu() {
        Region auth = ComponentBuilder.create(Component.LABEL, "АВТОРИЗАЦІЯ");
        TextField login = (TextField) ComponentBuilder.create(Component.FIELD, "Ваш логін");
        PasswordField password = (PasswordField) ComponentBuilder.create(Component.PASSWORD_FIELD, "Ваш пароль");
        Region confirmAuthorization = ComponentBuilder.getButton("ВХІД", BUTTON_OPACITY, TEXT_COLOR, BUTTON_COLOR);
        Region optionsBack = ComponentBuilder.getButton("НАЗАД", BUTTON_OPACITY, TEXT_COLOR, BUTTON_COLOR);

        optionsBack.setOnMouseClicked(event -> MenuBox.setSubMenu(mainMenu));
        confirmAuthorization.setOnMouseClicked(event -> authorization(login.getText(), password.getText()));

        return new SubMenu(auth, login, password, confirmAuthorization, optionsBack);
    }

    public static SubMenu getRegistrationMenu() {
        Region reg = ComponentBuilder.create(Component.LABEL, "РЕЄСТРАЦІЯ");
        TextField login = (TextField) ComponentBuilder.create(Component.FIELD, "Ваш логін");
        PasswordField password = (PasswordField) ComponentBuilder.create(Component.PASSWORD_FIELD, "Ваш пароль");
        Region confirmRegistration = ComponentBuilder.getButton("ЗАРЕЄСТРУВАТИСЯ", BUTTON_OPACITY, TEXT_COLOR, BUTTON_COLOR);
        Region optionsBack = ComponentBuilder.getButton("НАЗАД", BUTTON_OPACITY, TEXT_COLOR, BUTTON_COLOR);

        optionsBack.setOnMouseClicked(event -> MenuBox.setSubMenu(mainMenu));
        confirmRegistration.setOnMouseClicked(event -> registration(login.getText(), password.getText()));

        return new SubMenu(reg, login, password, confirmRegistration, optionsBack);
    }

    private static void authorization(String login, String password) {
        new Thread(() -> {
            User user = Client.getUser(login, password);

            if (user != null) {
                Platform.runLater(() -> Messenger.showMessage(SUCCESSFUL_AUTHORIZATION));
                try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
                Platform.runLater(StartMenu::goToAccount);
            } else
                Platform.runLater(() -> Messenger.showMessage(UNSUCCESSFUL_REGISTRATION));
        }).start();
    }

    private static void registration(String login, String password) {
        new Thread(() -> {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);

            if (Client.addUser(user)) {
                Platform.runLater(() -> Messenger.showMessage(SUCCESSFUL_REGISTRATION));
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
                Platform.runLater(() -> MenuBox.setSubMenu(StartMenu.authorizationMenu));
            } else
                Platform.runLater(() -> Messenger.showMessage(UNSUCCESSFUL_REGISTRATION));
        }).start();
    }

    private static void goToAccount() {
        MenuBox.hide();
        Main.getRoot().getChildren().add(SnakePane.instance);
        Main.getRoot().getChildren().add(MainMenu.instance);
        SnakesPane.init();
    }

}