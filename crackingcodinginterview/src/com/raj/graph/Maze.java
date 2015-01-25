package com.raj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: sraj1
 * Date:    10/30/12
 */
public class Maze {

    int nRows, nCols;
    boolean board[][];
    boolean isVisited[][]; // do restore these flags to original state after traversal
    List<Position> pathList;

    public Maze(int r, int c) {
        nRows = r;
        nCols = c;
        board = new boolean[nRows][nCols];
        isVisited = new boolean[nRows][nCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                board[i][j] = Math.random() > 0.4 ? true : false;
            }
        }
        pathList = new ArrayList<Position>();
    }

    public static void main(String[] args) {
        Maze maze = new Maze(8, 8);
        maze.printMaze();
        maze.findPath(0, 0, 7, 7);
    }

    public void printMaze() {
        for (int i = 0; i < nRows; i++) {
            System.out.println();
            for (int j = 0; j < nCols; j++) {
                int k = board[i][j] ? 1 : 0;
                System.out.print(k + " ");
            }
        }
    }

    private void restoreVisistedFlags() {
        isVisited = new boolean[nRows][nCols];
    }

    public void findPath(int srcRow, int srcCol, int destRow, int destCol) {
        Position srcPos = new Position(srcRow, srcCol);
        Position destPos = new Position(destRow, destCol);

        if (doBFS(srcPos, destPos)) {

            System.out.println("\nFound Path : ");

            for (Position p : pathList)
                System.out.println(p);

        } else {
            System.out.println("\nPath doesn't exist!");
        }

        restoreVisistedFlags();
    }

    private boolean doBFS(Position srcPos, Position destPos) {

        // bound checks
        if (srcPos == null || srcPos.getRow() < 0 || srcPos.getRow() >= nRows) return false;
        if (srcPos == null || srcPos.getCol() < 0 || srcPos.getCol() >= nCols) return false;

        // start and end pos should be valid
        if (!board[srcPos.getRow()][srcPos.getCol()] || !board[destPos.getRow()][destPos.getCol()]) return false;

        Block block = new Block();
        block.setPath(new ArrayList<Position>(), srcPos);
        block.setPosition(srcPos);
        Queue<Block> queue = new LinkedList<Block>();
        queue.add(block);

        while (!queue.isEmpty()) {
            Block currBlock = queue.remove();
            if (currBlock.getPosition().equals(destPos)) {
                // found the shortest path, save as final path...BFS always finds the shortest path!
                pathList = currBlock.getPath();
                return true;
            }

            // Mark as visited to avoid loops
            isVisited[currBlock.getPosition().getRow()][currBlock.getPosition().getCol()] = true;

            // check neighbor blocks to see if there is a path ahead, diagonal is allowed
            for (int i = -1; i <= 1; i++)
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue; // don't check with this block itself, only with neighbors!
                    int currRow = currBlock.getPosition().getRow() + i;
                    int currCol = currBlock.getPosition().getCol() + j;
                    if (currRow < 0 || currRow >= nRows) continue;
                    if (currCol < 0 || currCol >= nCols) continue;
                    if (board[currRow][currCol] && !isVisited[currRow][currCol]) {
                        // this is a valid path forward, take it
                        Block prospectiveBlock = new Block();
                        Position thisPos = new Position(currRow, currCol);
                        prospectiveBlock.setPosition(thisPos);
                        prospectiveBlock.setPath(currBlock.getPath(), thisPos);  // stores path till now
                        queue.add(prospectiveBlock);
                    }
                }
        }

        // all board places exhausted, no path found
        return false;
    }

    private class Position {
        int row, col;

        Position(int r, int c) {
            row = r;
            col = c;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;

            Position position = (Position) o;

            if (col != position.col) return false;
            if (row != position.row) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    private class Block {
        List<Position> path = new ArrayList<Position>();
        Position position;

        public List<Position> getPath() {
            return path;
        }

        public void setPath(List<Position> beforePath, Position newPos) {
            for (Position p : beforePath)
                this.path.add(p);
            path.add(newPos);
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }
    }

}
