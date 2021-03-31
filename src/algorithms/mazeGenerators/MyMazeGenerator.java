package algorithms.mazeGenerators;
import java.util.*;

public class MyMazeGenerator  extends AMazeGenerator {

    public Maze generate(int rows, int columns) {

        Maze myMaze = new Maze();
        myMaze.setRowNum(rows);
        myMaze.setColNum(columns);
        int[][] gridTable = buildGridKruskal(rows, columns);
        ArrayList<Position> wallsList = new ArrayList<>();
        ArrayList<Set<Integer>> listOfCellsSet =  createWallsAndCells(gridTable, wallsList);
        System.out.println(wallsList);
        System.out.println("*************************");
        System.out.println(listOfCellsSet);
        generateKruskal(gridTable, wallsList, listOfCellsSet);

        for(int i = 0; i < rows ; ++i) {
            for(int j = 0; j < columns; ++j) {
                System.out.print(gridTable[i][j]);
                System.out.print(" ");
            }

            System.out.println();
        }

        myMaze.setMazeTable(gridTable);
        return myMaze;

    }


    private int[][] buildGridKruskal(int rows, int columns) {
        int[][] gridTable = new int[rows][columns];
        int counter = 2;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i % 2 == 0 && j % 2 == 0 ) {
                    gridTable[i][j] = counter;
                    counter ++;
                } else {
                        gridTable[i][j] = 1;
                    }
                }
            }
        if (rows % 2 == 0 ){    // even number of rows - adjust the numbers to be 1010..10  instead of walls 111...11.
            for (int k = 1; k < columns; k++){
                if (k % 2 != 0){
                gridTable[rows-1][k] = counter;
                counter ++;
                }
            }
        }
        return gridTable;
    }

    private ArrayList<Set<Integer>> createWallsAndCells(int[][] gridTable, ArrayList<Position> wallsList) {
        ArrayList<Set<Integer>> listOfCellsSet = new ArrayList<>(); // ArrayList of walls
        for (int i = 0; i < gridTable.length; i++) {
            for (int j = 0; j < gridTable[i].length; j++) {
                if (gridTable[i][j] == 1) {
                    if (checkWall(gridTable,i,j)) {
                        Position pos = new Position(i, j);
                        wallsList.add(pos);
                    }
                } else {
                    Set set = new HashSet(gridTable[i][j]);
                    listOfCellsSet.add(set);
                }
            }
        }
        if (gridTable.length % 2 == 0){
            Position pos = new Position(gridTable.length-1, 0);
            wallsList.remove(pos); 
        }
        return listOfCellsSet;
    }

    private boolean checkWall(int[][] gridTable, int i, int j ) {
        if(j != 0  && j != gridTable[i].length-1 && gridTable[i][j+1] != 1 && gridTable[i][j-1] != 1 )
        {
            return true;
        }
        else if(i != 0  && i != gridTable.length-1 && gridTable[i+1][j] != 1 && gridTable[i-1][j] != 1) {
            return true;
        }
        return false;
    }

    private void generateKruskal(int[][] gridTable, ArrayList<Position> wallsList, ArrayList<Set<Integer>> listOfCellsSet) {

        while (!wallsList.isEmpty()) {
            Position posWall = extractWallRandom(wallsList);
            int currRow = posWall.getRowIndex();
            int currCol = posWall.getColIndex();

            if (currRow % 2 == 0 && currRow!=0) {
                int neighUp = gridTable[currRow - 1][currCol];
                int neighDown = gridTable[currRow + 1][currCol];
                if (neighUp != neighDown) { // not in the same set
                    changeValue(gridTable, neighUp, neighDown);// merge the other cell
                    gridTable[currRow][currCol] = neighUp;  // change the wall to a cell with the same value
                }
            }
            else {
                int neighLeft = gridTable[currRow][currCol - 1];
                int neighRight = gridTable[currRow][currCol + 1];
                if (neighLeft != neighRight) { // not in the same set
                    changeValue(gridTable, neighLeft,neighRight);  // merge the other cell
                    gridTable[currRow][currCol] = neighLeft;    // change the wall to a cell with the same value
                }
            }
        }
    }

    private void changeValue(int[][] gridTable, int currvalue, int neighvalue) {

        for (int i = 0; i < gridTable.length ; i++) {
            for (int j = 0; j < gridTable[i].length ; j++) {
                if (gridTable[i][j] == neighvalue) {
                    gridTable[i][j] = currvalue;
                }
            }
        }
    }

    private Position extractWallRandom(ArrayList<Position> wallsList) {
        Random rand = new Random();
        Position pos = wallsList.get(rand.nextInt(wallsList.size()));
        wallsList.remove(pos);
        return pos;
    }

    @Override
    public Position genStart(Maze Maze) {
        return null;
    }

    @Override
    public Position genGoal(Maze Maze) {
        return null;
    }

    @Override
    public int[][] genTable(Maze Maze) {
        return new int[0][];
    }
}
