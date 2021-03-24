package algorithms.mazeGenerators;
import java.util.*;

public class MyMazeGenerator  extends AMazeGenerator {

    public Maze generate(int rows, int columns) {
        int[][] gridTable = buildGridKruskal(rows, columns);
        ArrayList<Position> wallsList = wallsList(gridTable);
        generateKruskal(gridTable, wallsList);

        Maze myMaze = new Maze();
        myMaze.setRowNum(rows);
        myMaze.setColNum(columns);
        myMaze.setMazeTable(gridTable);
        //myMaze.getStartPosition(Position(0,0));
        return myMaze;

    }


    private int[][] buildGridKruskal(int rows, int columns) {
        // init the grid table with borders which means added 4 edges of walls.
        int[][] gridTable = new int[rows + 2][columns + 2];
        int counter = 2;
        for (int i = 0; i < rows + 2; i++) {
            for (int j = 0; j < columns + 2; j++) {
                if (i % 2 == 0) {
                    gridTable[i][j] = 1;
                } else {
                    if (j % 2 == 0) {
                        gridTable[i][j] = 1;
                    }
                    gridTable[i][j] = counter;
                    counter++;
                }
            }
        }
        return gridTable;
    }

    private ArrayList<Position> wallsList(int[][] gridTable) {
        ArrayList<Position> wallsList = new ArrayList<>(); // ArrayList of walls:value == 1 without borders.
        for (int i = 1; i < gridTable.length - 1; i++) {
            for (int j = 1; j < gridTable[i].length - 1; j++) {
                if (gridTable[i][j] == 1) {
                    Position pos = new Position(i, j);
                    wallsList.add(pos);
                }
            }
        }
        return wallsList;
    }

    private void generateKruskal(int[][] gridTable, ArrayList<Position> wallsList) {

        while (!wallsList.isEmpty()) {
            Position posWall = extractWallRandom(wallsList);
            int currRow = posWall.getRowIndex();
            int currCol = posWall.getColIndex();

            if (currRow % 2 == 0) {
                int neighUp = gridTable[currRow - 1][currCol];
                int neighDown = gridTable[currRow + 1][currCol];
                if (neighUp != neighDown) { // not in the same set
                    gridTable[currRow + 1][currCol] = neighUp; // merge the other cell
                    gridTable[currRow][currCol] = neighUp;  // change the wall to a cell with the same value
                }
            }
            int neighLeft = gridTable[currRow][currCol - 1];
            int neighRight = gridTable[currRow][currCol + 1];
            if (neighLeft != neighRight) { // not in the same set
                gridTable[currRow][currCol + 1] = neighLeft;  // merge the other cell
                gridTable[currRow][currCol] = neighLeft;    // change the wall to a cell with the same value
            }
        }
    }

    private Position extractWallRandom(ArrayList<Position> wallsList) {
        Random rand = new Random();
        Position pos = wallsList.get(rand.nextInt(wallsList.size()));
        wallsList.remove(pos);
        return pos;
    }

}
