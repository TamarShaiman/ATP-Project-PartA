package algorithms.mazeGenerators;
import java.util.*;

public class MyMazeGenerator  extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {

        Maze myMaze = new Maze();
        myMaze.setColNum(columns);
        myMaze.setRowNum(rows);
        int[][] gridTable = generateGrid(rows, columns);
        myMaze.setMazeTable(gridTable);
        primGenerate(myMaze , rows, columns);
        return myMaze;
    }

    private void primGenerate(Maze myMaze, int rows, int columns) {
        Position[] wallList = initWallList(myMaze, rows, columns);
        int[][] mazeMatrix = initMazeMatrix(myMaze, rows, columns);
        myMaze.setStart(genStart(myMaze));

    }

    private int[][] generateGrid(int rows, int columns) {
        int[][] gridTable = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i % 2 == 0 && j % 2 == 0 ) {
                    gridTable[i][j] = 0;
                } else {
                    gridTable[i][j] = 1;
                }
            }
        }
        return gridTable;
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

/*

    public Maze generate(int rows, int columns) {

        Maze myMaze = new Maze();
        myMaze.setRowNum(rows);
        myMaze.setColNum(columns);
        int[][] gridTable = buildGridKruskal(rows, columns);
        ArrayList<Position> wallsList = new ArrayList<>();
        createWalls(gridTable, wallsList);
        generateKruskal(gridTable, wallsList);
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
                    gridTable[i][j] = 1; //TODO: maybe use method set rows to 1
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

    private void createWalls(int[][] gridTable, ArrayList<Position> wallsList) {
        for (int i = 0; i < gridTable.length; i++) {
            for (int j = 0; j < gridTable[i].length; j++) {
                if (gridTable[i][j] == 1) {
                    if (checkWall(gridTable,i,j)) {
                        Position pos = new Position(i, j);
                        wallsList.add(pos);
                    }
                }
            }
        }
        if (gridTable.length % 2 == 0){
            Position pos = new Position(gridTable.length-1, 0);
            wallsList.remove(pos);
        }
    }

    private boolean checkWall(int[][] gridTable, int i, int j ) {
        if(j != 0  && j != gridTable[i].length-1 && gridTable[i][j+1] != 1 && gridTable[i][j-1] != 1 )
        {
            return true;
        }
        else return i != 0 && i != gridTable.length - 1 && gridTable[i + 1][j] != 1 && gridTable[i - 1][j] != 1;
    }

    private void generateKruskal(int[][] gridTable, ArrayList<Position> wallsList) {
        int neighA, neighB;
        ArrayList<Set<Integer>> listOfCellsSet = new ArrayList<>(); // ArrayList of cells

        while (!wallsList.isEmpty()) {
            Position posWall = extractWallRandom(wallsList);
            int currRow = posWall.getRowIndex();
            int currCol = posWall.getColIndex();

            if (currRow % 2 == 0 || (currRow == gridTable.length-1)) {
                neighA = gridTable[currRow][currCol - 1];
                neighB = gridTable[currRow][currCol + 1];
            }
            else {
                neighA = gridTable[currRow-1][currCol];
                neighB = gridTable[currRow+1][currCol];
            }

            if (neighA != neighB) { // not in the same set
                Boolean ans = mergeCells(listOfCellsSet,neighA,neighB);  // merge the sets cell
                if(ans){gridTable[currRow][currCol] = 0;  }  // change the wall to a cell with the 0 value
            }
        }
    }

    private Boolean mergeCells(ArrayList<Set<Integer>> listOfCellsSet,int destValue, int srcValue) {
        int indexA = -1,indexB = -1;
        for (int i = 0; i < listOfCellsSet.size(); i++) {
            if (listOfCellsSet.get(i).contains(destValue)) {
                indexA = i;
            }
            if (listOfCellsSet.get(i).contains(srcValue)) {
                indexB = i;
            }
        }
        if (indexA == -1 && indexB == -1)
        {
            Set set = new HashSet();
            set.add(destValue);
            set.add(srcValue);
            listOfCellsSet.add(set);
        }
        else if (indexA == -1 && indexB != -1)
        {
            listOfCellsSet.get(indexB).add(destValue);
        }
        else if (indexA != -1 && indexB == -1)
        {
            listOfCellsSet.get(indexA).add(srcValue);
        }
        else if ((indexA != -1 && indexB == indexA)){
            return false;
        }
        else {
            listOfCellsSet.get(indexA).addAll(listOfCellsSet.get(indexB));
            listOfCellsSet.remove(indexB);
        }
        return true;
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
    }*/
