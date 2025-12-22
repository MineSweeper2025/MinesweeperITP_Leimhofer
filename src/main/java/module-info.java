module htl.steyr.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens htl.steyr.minesweeper to javafx.fxml;
    exports htl.steyr.minesweeper;
}