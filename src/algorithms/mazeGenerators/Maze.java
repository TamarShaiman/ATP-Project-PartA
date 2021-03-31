//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package algorithms.mazeGenerators;

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

    public void print() {
        for(int i = 0; i < this.rowNum; ++i) {
            for(int j = 0; j < this.colNum; ++j) {
                System.out.print(this.mazeTable[i][j]);
                System.out.print(" ");
            }

            System.out.println();
        }

    }

    public Position getStartPosition() {
        return this.start;
    }

    public Position getGoalPosition() {
        return this.goal;
    }

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
        return this.rowNum;
    }

    public int getColNum() {
        return this.colNum;
    }
}
