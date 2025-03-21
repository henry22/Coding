package Recursion;

import java.util.ArrayList;

/**
 * Maze
 * Given a maze and a start point and a target point, return the path to reach the
target.
 */

public class MazeReturnPath {
  public static void main(String[] args) {
    char[][] maze = {
      {'.', 'X', '.', '.', '.', 'X'},
      {'.', '.', '.', 'X', '.', 'X'},
      {'X', 'X', '.', 'X', '.', '.'},
      {'.', 'X', 'X', 'X', '.', 'X'},
      {'.', '.', '.', '.', '.', 'X'},
      {'.', '.', '.', '.', '.', '.'}
    };
    ArrayList<Character> path = new ArrayList<>();
    MazeReturnPath mazeReturnPath = new MazeReturnPath();
    boolean result = mazeReturnPath.solveMaze(maze, 0, 0, 5, 5, path);
    System.out.println(result);
    System.out.println(path);
  }

  public boolean solveMaze(char[][] maze, int startX, int startY, int targetX, int targetY, ArrayList<Character> path) {
    if(startX < 0 || startX >= maze.length || startY < 0 || startY >= maze[0].length || maze[startX][startY] == 'X') {
      return false;
    }

    if(startX == targetX && startY == targetY) {
      return true;
    }

    maze[startX][startY] = 'X';

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    char[] direction = {'D', 'R', 'U', 'L'};

    for(int i = 0; i < 4; i++) {
      // Add the direction to the path
      path.add(direction[i]);

      if(solveMaze(maze, startX + dx[i], startY + dy[i], targetX, targetY, path)) {
        return true;
      }

      // Backtracking to keep the subproblem has the same state as the original problem
      path.remove(path.size() - 1);
    }

    return false;
  }
}
