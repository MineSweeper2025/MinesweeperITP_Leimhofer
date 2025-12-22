package htl.steyr.minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.sql.Array;

public class HelloController {
    public GridPane gameField;
    int numRows= gameField.getRowCount();
    int numCols= gameField.getColumnCount();
    public Button button[]=new Button[numCols*numRows];

    public void onButtonClicked(ActionEvent actionEvent) {
    }
}
