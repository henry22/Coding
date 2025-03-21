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

public class MazeCodingStyle {

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
    MazeCodingStyle mazeCodingStyle = new MazeCodingStyle();
    boolean result = mazeCodingStyle.solveMaze(maze, 0, 0, 5, 5);
    System.out.println(result);
  }

  public boolean solveMaze(char[][] maze, int startX, int startY, int targetX, int targetY) {
    if(startX < 0 || startX >= maze.length || startY < 0 || startY >= maze[0].length || maze[startX][startY] == 'X') {
      return false;
    }

    if(startX == targetX && startY == targetY) {
      return true;
    }

    maze[startX][startY] = 'X';

    // Right, Down, Left, Up
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    // Try all 4 directions
    for(int i = 0; i < 4; i++) {
      if(solveMaze(maze, startX + dx[i], startY + dy[i], targetX, targetY)) {
        return true;
      }
    }

    return false;
  }
}
