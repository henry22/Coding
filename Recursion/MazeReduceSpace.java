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

public class MazeReduceSpace {

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
    MazeReduceSpace mazeOptimized = new MazeReduceSpace();
    boolean result = mazeOptimized.solveMaze(maze, 0, 0, 5, 5);
    System.out.println(result);
  }

  // We can reduce the space complexity by not using the visited array
  public boolean solveMaze(char[][] maze, int startX, int startY, int targetX, int targetY) {
    if(startX < 0 || startX >= maze.length || startY < 0 || startY >= maze[0].length || maze[startX][startY] == 'X') {
      return false;
    }

    if(startX == targetX && startY == targetY) {
      return true;
    }

    // Mark the current cell as wall
    maze[startX][startY] = 'X';

    if(solveMaze(maze, startX + 1, startY, targetX, targetY) ||
      solveMaze(maze, startX - 1, startY, targetX, targetY) ||
      solveMaze(maze, startX, startY + 1, targetX, targetY) ||
      solveMaze(maze, startX, startY - 1, targetX, targetY)) {
      return true;
    }

    return false;
  }
}
