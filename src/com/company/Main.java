package com.company;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
        MyMazeGenerator MMG = new MyMazeGenerator();
        System.out.println(MMG.measureAlgorithmTimeMillis(10,10));
        Maze MMaze = MMG.generate(10, 10);
        MMaze.print();
    }
}
