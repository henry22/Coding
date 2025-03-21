package Recursion;

/**
 * Maze
 * Given a maze and a start point and a target point, return whether the target can
be reached.
 *
 * Example Input:
 * Start Point: (0, 0); Target Point (5, 5);
 * Maze: char[][] = {
 * {'.', 'X', '.', '.', '.', 'X'},
 * {'.', '.', '.', 'X', '.', 'X'},
 * {'X', 'X', '.', 'X', '.', '.'},
 * {'.', 'X', 'X', 'X', '.', 'X'},
 * {'.', '.', '.', '.', '.', 'X'},
 * {'.', '.', '.', '.', '.', '.'}
 * }
 * Example Output: True
 *
 */

public class Maze {

  /**
   * @param args
   */
  public static void main(String[] args) {
    char[][] maze = {
      {'.', 'X', '.', '.', '.', 'X'},
      {'.', '.', '.', 'X', '.', 'X'},
      {'X', 'X', '.', 'X', '.', '.'},
      {'.', 'X', 'X', 'X', '.', 'X'},
      {'.', '.', '.', '.', '.', 'X'},
      {'.', '.', '.', '.', '.', '.'}
    };
    Maze mazeSimple = new Maze();
    boolean result = mazeSimple.solveMaze(maze, 0, 0, 5, 5, new boolean[6][6]);
    System.out.println(result);
  }

  public boolean solveMaze(char[][] maze, int startX, int startY, int targetX, int targetY, boolean[][] visited) {
    // Check if the start point is out of bounds or is a wall or has been visited
    if(startX < 0 || startX >= maze.length || startY < 0 || startY >= maze[0].length || maze[startX][startY] == 'X' || visited[startX][startY]) {
      return false;
    }

    if(startX == targetX && startY == targetY) {
      return true;
    }

    visited[startX][startY] = true;

    if(solveMaze(maze, startX + 1, startY, targetX, targetY, visited) ||
      solveMaze(maze, startX - 1, startY, targetX, targetY, visited) ||
      solveMaze(maze, startX, startY + 1, targetX, targetY, visited) ||
      solveMaze(maze, startX, startY - 1, targetX, targetY, visited)) {
      return true;
    }

    return false;
  }
}
