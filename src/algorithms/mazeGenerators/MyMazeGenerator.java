package algorithms.mazeGenerators;
import java.util.*;

public class MyMazeGenerator  extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze myMaze = new Maze();
        myMaze.setColNum(columns);
        myMaze.setRowNum(rows);
        int[][] gridTable = generateGrid(rows, columns); //init all Maze Cell to 2 to indicate that they are not part of the Maze (from Prim's algorithm)
        myMaze.setMazeTable(gridTable);
        primGenerate(myMaze , rows, columns);
        return myMaze;
    }

    private void primGenerate(Maze myMaze, int rows, int columns) {
        ArrayList<Position> wallList = initWallList();
        //boolean[][] cellsInMazeMatrix = initMazeNeighMatrix(myMaze, rows, columns); //initiate all the cells in the matrix to false
        myMaze.setStart(genStart(myMaze));
        Position startPos = myMaze.getStartPosition();
        myMaze.setGoal(genGoal(myMaze));
        addWallsNeighToWL(myMaze, rows, columns,wallList, startPos);
        while(!wallList.isEmpty()){
            Position currWall = extractWallRandom(wallList);
            Position neigh = findSeparatedCell(myMaze, currWall); //neigh is the cell that is not part of the maze - if exists
            if (!(neigh == null)){
                //Make the wall a passage
                myMaze.setCell0(currWall.getRowIndex(), currWall.getColIndex());
                //mark the unvisited cell as part of the maze
                myMaze.setCell0(neigh.getRowIndex(), neigh.getColIndex());
                //Add the neighboring walls of the cell to the wall list
                addWallsNeighToWL(myMaze, rows, columns,wallList, neigh);
            }
            wallList.remove(currWall);
        }
    }

    private Position genGoal(Maze myMaze) {
        Position goalPos = new Position(myMaze.getRowNum()-1 , myMaze.getColNum()-1);
        breakGoal(myMaze, goalPos);
        return goalPos;
    }

    private void breakGoal(Maze myMaze, Position goalPos) {
        myMaze.setCell0(goalPos.getRowIndex(), goalPos.getColIndex());
        myMaze.setCell0(goalPos.getRowIndex(), goalPos.getColIndex()-1);
    }

    private Position genStart(Maze myMaze) {
        Position startPos = new Position(0 , 0);
        myMaze.setCell0(0,0);
        return  startPos;
    }

    private Position findSeparatedCell(Maze myMaze, Position currWall) { // returns the neighbor cell that is not part of the maz, if exist
        int currRow = currWall.getRowIndex();
        int currCol = currWall.getColIndex();
        int neighA, neighB;
        if (currRow % 2 == 0) { // A is Right to currWall, B is Left to currWall
            neighA = myMaze.getCellValue(currRow,currCol - 1);
            neighB =  myMaze.getCellValue(currRow,currCol + 1);
            if (neighA == 0 && neighB == 2){
                return new Position(currRow,currCol + 1); //return neigh B
            }
            else if (neighA == 2 && neighB == 0){
                return new Position(currRow,currCol - 1); //return neigh A
            }
        }
        else { // A is Below currWall, B is Above currWall
            neighA = myMaze.getCellValue(currRow - 1, currCol);
            neighB = myMaze.getCellValue(currRow + 1, currCol);
            if (neighA == 0 && neighB == 2){
                return new Position(currRow + 1, currCol); //return neigh B
            }
            else if (neighA == 2 && neighB == 0){
                return new Position(currRow - 1, currCol); //return neigh A
            }
        }
        return null;
    }

    private void addWallsNeighToWL(Maze myMaze,int rows, int columns, ArrayList<Position> wallList, Position cellPos) {
        int currRow = cellPos.getRowIndex();
        int currCol = cellPos.getColIndex();
        if (currRow != 0) {
            addWallToMaze(myMaze, wallList, currRow - 1, currCol);
        }
        if (currRow != rows-1){
            addWallToMaze(myMaze, wallList, currRow + 1, currCol);
        }
        if (currCol != 0){
            addWallToMaze(myMaze, wallList, currRow, currCol-1);
        }
        if (currCol != columns-1){
            addWallToMaze(myMaze, wallList, currRow, currCol + 1);
        }
    }

    private void addWallToMaze (Maze myMaze, ArrayList<Position> wallList, int row, int col){
        Position neigh = new Position(row, col);
        if (myMaze.getCellValue(row, col) == 3 ) {
            myMaze.setCell1(row, col);
            wallList.add(neigh);
        }
    }

    private ArrayList<Position> initWallList() {
        ArrayList<Position> wallsList = new ArrayList<>();
        return wallsList;
    }

    private int[][] generateGrid(int rows, int columns) {
        int[][] gridTable = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i % 2 == 0 && j % 2 == 0 ) {
                    gridTable[i][j] = 2;
                }
                else if (i % 2 == 1 && j % 2 == 1 ) {
                    gridTable[i][j] = 1;
                }
                else {
                    gridTable[i][j] = 3;
                }

            }
        }
        return gridTable;
    }

    private Position extractWallRandom(ArrayList<Position> wallsList) {
        Random rand = new Random();
        Position pos = wallsList.get(rand.nextInt(wallsList.size()));
        wallsList.remove(pos);
        return pos;
    }

}

