package user_interface.account.battlefield;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import main.Main;
import main.SnakePane;
import main.WindowSettings;
import nodes.Line;

import java.util.ArrayList;
import java.util.List;

import static user_interface.account.battlefield.RenderField.SQUARE_NUMBER;
import static user_interface.account.battlefield.RenderField.parts;
import static user_interface.account.battlefield.RenderField.startPoint;


public class Grid {

    private List<Node> nodes = new ArrayList<>();
    public static final Grid instance = new Grid();

    private Grid() {
        for (int i = 0 ; i < SQUARE_NUMBER + 1; i++) {
            nodes.add(new Line(startPoint + parts[i], 0, startPoint + parts[i], WindowSettings.height));
            nodes.add(new Line(startPoint, parts[i], startPoint + WindowSettings.height, parts[i]));
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
