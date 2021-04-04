package algorithms.mazeGenerators;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;
import java.util.Arrays;

public class Maze {
    private int rowNum;
    private int colNum;
    private Position start;
    private Position goal;
    private int[][] mazeTable;

    public Maze(int rowNum, int colNum, Position start, Position goal, int[][] mazeTable) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.start = start;
        this.goal = goal;
        this.mazeTable = mazeTable;
    }

    public Maze() {
    }

    public void print(){
        //System.out.println(Arrays.deepToString(mazeTable));
        for (int i = 0 ; i < rowNum ; i++ ){
            System.out.print("{ ");
            for (int j = 0 ; j < colNum ; j++){
//                System.out.print(mazeTable[i][j]);
                if (mazeTable[i][j] == 0){
                    if (this.getStartPosition().getRowIndex() == i && this.getStartPosition().getColIndex() == j){
                        System.out.print("S ");
                    }
                    else if (this.getGoalPosition().getRowIndex() == i && this.getGoalPosition().getColIndex() == j){
                        System.out.print("E ");
                    }
                    else{
                        System.out.print(". "); //TODO: change back to 0
                    }
                }
                else{
                    System.out.print("█ "); //TODO: change back to 1
                }
            }
            System.out.println("}");
            //System.out.println(); //ends the line after each row
        }
    }

    public void setCell1(int row, int col){
        this.mazeTable[row][col] = 1;
    }

    public void setCell0(int row, int col){
        this.mazeTable[row][col] = 0;
    }

    public void setColToOnes(int col, int startInd, int endInd) {
        for (int i = startInd; i <= endInd; i++) {
            this.setCell1(i, col);
        }
    }

    public void setRowToOnes(int row, int startInd, int endInd) {
        for (int i = startInd; i <= endInd; i++) {
            this.setCell1(row, i);
        }
    }

    public Position getStartPosition() {
        return this.start;
    }

    public Position getGoalPosition() { return this.goal; }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setGoal(Position goal) {
        this.goal = goal;
    }

    public void setMazeTable(int[][] mazeTable) {
        this.mazeTable = mazeTable;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public int getCellValue(int row, int col) {
        if (row < this.getRowNum() && col < this.getColNum() && row >= 0 && col >=0) {
            return this.mazeTable[row][col];
        }
        else {
            return -1;
        }
    }
}
