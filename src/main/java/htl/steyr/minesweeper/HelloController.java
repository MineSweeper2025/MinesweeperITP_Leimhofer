package htl.steyr.minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import javax.swing.*;
import java.sql.Array;
import java.util.Random;

public class HelloController {
    public GridPane gameField;
    public Button fieldButton;
    public Label numBombs;
    public Label bombLabel;
    public AnchorPane buttonPane;
    private int numFlags;
    private boolean flag;
    public void load(int numRows, int numCols, int numMines){
        int numFields=numCols*numRows;
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(1.0/numCols);
        RowConstraints r1 = new RowConstraints();
        r1.setPercentHeight(1.0/numRows);
        for (int i = 0; i < numCols; i++) {
            gameField.getColumnConstraints().add(c1);
        }
        for (int i = 0; i < numRows; i++) {
            gameField.getRowConstraints().add(r1);
        }
        Random rand=new Random();
        for(int i=0;i<numRows;i++){
            for(int j=0;j<numCols;j++){
                gameField.add(buttonPane,j,i);
                if(rand.nextBoolean()&&numMines<numFields&&numMines>0){
                    bombLabel.setVisible(true);
                    numBombs.setVisible(false);
                    numMines--;
                    numFields--;
                } else if (numMines==numFields) {
                    bombLabel.setVisible(true);
                    numBombs.setVisible(false);
                    numMines--;
                    numFields--;
                }else {
                    bombLabel.setVisible(false);
                    numFields--;
                }
            }
        }
    }

    public void onButtonClicked(ActionEvent actionEvent) {

        reveal(GridPane.getRowIndex(gameField),GridPane.getColumnIndex(gameField));
    }

    private void checkFields(int row, int col) {
        int numMinesNearby=0;
        if(row==0&&col==0){
            numMinesNearby+=checkField(row,col+1);
            numMinesNearby+=checkField(row+1,col);
            numMinesNearby+=checkField(row+1,col+1);
        } else if (row==gameField.getRowCount()-1&&col==gameField.getColumnCount()-1) {
            numMinesNearby+=checkField(row-1,col-1);
            numMinesNearby+=checkField(row-1,col);
            numMinesNearby+=checkField(row,col-1);
        }else if(row==gameField.getRowCount()-1&&col==0) {
            numMinesNearby+=checkField(row-1,col);
            numMinesNearby+=checkField(row, col+1);
            numMinesNearby+=checkField(row-1,col+1);
        }else if (row==0 && col==gameField.getColumnCount()-1) {
            numMinesNearby+=checkField(row-1,col-1);
            numMinesNearby+=checkField(row-1,col-1);
            numMinesNearby+=checkField(row+1,col);
        }else if(row==0){
            numMinesNearby+=checkField(row,col+1);
            numMinesNearby+=checkField(row+1,col+1);
            numMinesNearby+=checkField(row+1,col);
            numMinesNearby+=checkField(row+1,col-1);
            numMinesNearby+=checkField(row, col-1);
        } else if (col==0) {
            numMinesNearby+=checkField(row-1,col);
            numMinesNearby+=checkField(row-1,col+1);
            numMinesNearby+=checkField(row,col+1);
            numMinesNearby+=checkField(row+1,col);
            numMinesNearby+=checkField(row+1,col+1);
        }else if(row==gameField.getRowCount()-1){
            numMinesNearby+=checkField(row,col+1);
            numMinesNearby+=checkField(row,col-1);
            numMinesNearby+=checkField(row-1,col);
            numMinesNearby+=checkField(row-1,col+1);
            numMinesNearby+=checkField(row-1,col-1);
        }
        else if (col==gameField.getColumnCount()-1) {
            numMinesNearby+=checkField(row+1,col);
            numMinesNearby+=checkField(row-1,col);
            numMinesNearby+=checkField(row-1,col-1);
            numMinesNearby+=checkField(row,col-1);
            numMinesNearby+=checkField(row+1,col-1);
        }
        else {
            numMinesNearby+=checkField(row+1,col);
            numMinesNearby+=checkField(row-1,col);
            numMinesNearby+=checkField(row-1,col-1);
            numMinesNearby+=checkField(row,col-1);
            numMinesNearby+=checkField(row+1,col-1);
            numMinesNearby+=checkField(row+1,col-1);
            numMinesNearby+=checkField(row+1,col+1);
            numMinesNearby+=checkField(row+1,col);
        }
        if (numMinesNearby==0){
            revealNearby(row,col);
        }else {
            numBombs.setVisible(true);
            numBombs.setText(String.valueOf(numMinesNearby));
        }
    }

    public void gameOver(){
        for(int i=0;i<gameField.getRowCount();i++){
            for(int j=0;j<gameField.getColumnCount();j++){
                fieldButton.setVisible(false);
            }
        }
    }
    public int checkField(int rowNum, int colNum){
        if (bombLabel.isVisible()){
            return 1;
        }
        return 0;
    }
    public void revealNearby(int row, int col){
        if(row==0&&col==0){
            reveal(row,col+1);
            reveal(row+1,col);
            reveal(row+1,col+1);
        } else if (row==gameField.getRowCount()-1&&col==gameField.getColumnCount()-1) {
            reveal(row-1,col-1);
            reveal(row-1,col);
            reveal(row,col-1);
        }else if(row==gameField.getRowCount()-1&&col==0) {
            reveal(row-1,col);
            reveal(row, col+1);
            reveal(row-1,col+1);
        }else if (row==0 && col==gameField.getColumnCount()-1) {
            reveal(row-1,col-1);
            reveal(row-1,col-1);
            reveal(row+1,col);
        }else if(row==0){
            reveal(row,col+1);
            reveal(row+1,col+1);
            reveal(row+1,col);
            reveal(row+1,col-1);
            reveal(row, col-1);
        } else if (col==0) {
            reveal(row-1,col);
            reveal(row-1,col+1);
            reveal(row,col+1);
            reveal(row+1,col);
            reveal(row+1,col+1);
        }else if(row==gameField.getRowCount()-1){
            reveal(row,col+1);
            reveal(row,col-1);
            reveal(row-1,col);
            reveal(row-1,col+1);
            reveal(row-1,col-1);
        } else if (col==gameField.getColumnCount()-1) {
            reveal(row+1,col);
            reveal(row-1,col);
            reveal(row-1,col-1);
            reveal(row,col-1);
            reveal(row+1,col-1);
        } else {
            reveal(row+1,col);
            reveal(row-1,col);
            reveal(row-1,col-1);
            reveal(row,col-1);
            reveal(row+1,col-1);
            reveal(row+1,col-1);
            reveal(row+1,col+1);
            reveal(row+1,col);
        }
    }
    public void reveal(int rowNum, int colNum){
        fieldButton.setVisible(false);
        if(bombLabel.isVisible()){
            gameOver();
        }else {
            checkFields(GridPane.getRowIndex(gameField), GridPane.getColumnIndex(gameField));
        }
    }
    public void placeFlag(int row, int collum){
        fieldButton.setText("Flag");
    }
    public void removeFlag(int row, int col){
        fieldButton.setText("");
    }
    public int getNumFlags() {
        return numFlags;
    }

    public void setNumFlags(int numFlags) {
        this.numFlags = numFlags;
    }

    public void onRightMouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.isSecondaryButtonDown()){
            if (isFlag()){
                removeFlag(GridPane.getRowIndex(gameField), GridPane.getColumnIndex(gameField));
                setFlag(false);
                setNumFlags(numFlags+1);
            }else {
                placeFlag(GridPane.getRowIndex(gameField), GridPane.getColumnIndex(gameField));
                setNumFlags(getNumFlags() - 1);
                setFlag(true);
            }
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
