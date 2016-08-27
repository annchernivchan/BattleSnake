package events;

import javafx.event.Event;
import javafx.scene.input.KeyCode;
import main.Main;
import main.SnakePane;
import user_interface.account.MainMenu;
import user_interface.account.battlefield.snake.Snake;
import javafx.scene.Scene;
import user_interface.animation.TransitionAnimation;

public class KeyboardEvents {

    public static Snake snake;
    private static Scene scene = Main.getScene();
    private static boolean lock;

    public static void initKeys(Scene scene) {
        scene.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.LEFT) snake.setDirection(Direction.LEFT);
//            if (event.getCode() == KeyCode.RIGHT) snake.setDirection(Direction.RIGHT);
//            if (event.getCode() == KeyCode.UP) snake.setDirection(Direction.UP);
//            if (event.getCode() == KeyCode.DOWN) snake.setDirection(Direction.DOWN);
        });
    }

    public static void setMode(Mode mode) {
        switch (mode) {
            case BATTLEFIELD_MODE: battlefieldMode();
        }
    }

    private static void battlefieldMode() {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                TransitionAnimation.start(SnakePane.instance, MainMenu.instance, ()-> scene.setOnKeyPressed(null));
            }
        });
    }

}
