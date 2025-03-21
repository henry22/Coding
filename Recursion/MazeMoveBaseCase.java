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

public class MazeMoveBaseCase {

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
    MazeMoveBaseCase mazeMoveBaseCase = new MazeMoveBaseCase();
    boolean result = mazeMoveBaseCase.solveMaze(maze, 0, 0, 5, 5);
    System.out.println(result);
  }

  public boolean solveMaze(char[][] maze, int startX, int startY, int targetX, int targetY) {
    if(startX == targetX && startY == targetY) {
      return true;
    }

    maze[startX][startY] = 'X';
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    for(int i = 0; i < 4; i++) {
      int newX = startX + dx[i];
      int newY = startY + dy[i];

      // Peek into new coordinates to see if they are out of bounds or are walls and skip them
      if(newX < 0 || newX >= maze.length || newY < 0 || newY >= maze[0].length || maze[newX][newY] == 'X') {
        continue;
      }

      if(solveMaze(maze, newX, newY, targetX, targetY)) {
        return true;
      }
    }

    return false;
  }
}
