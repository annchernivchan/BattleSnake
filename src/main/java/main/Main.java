package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import rendering.GridRender;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Main extends Application {

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static Pane root = new Pane();
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(root, screenSize.width, screenSize.height);

        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle("JavaFx Game. BattleSnake");

        stage = primaryStage;

        primaryStage.setOnCloseRequest(event -> Timer.stop());
        primaryStage.setScene(scene);
        primaryStage.show();

        GridRender.getInstance().render();

        Timer.start();

    }

    public static Pane getRoot() {
        return root;
    }
    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}