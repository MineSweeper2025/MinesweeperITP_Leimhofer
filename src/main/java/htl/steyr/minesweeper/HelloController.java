package htl.steyr.minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.Array;
import java.util.Random;

public class HelloController {
    public GridPane gameField;
    public Button fieldButton;
    public Label numBombs;
    public Label bombLabel;
    public AnchorPane buttonPane;
    int numRows= gameField.getRowCount();
    int numCols= gameField.getColumnCount();
    public void load(){
        int numMines=10;
        int numFields=64;
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
}
