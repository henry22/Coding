package Recursion;

/**
 * Maze
 * Given a maze and a start point and a target point, print out the path to reach the
target.
 */
public class MazePrintPath {

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
    MazePrintPath mazePrintPath = new MazePrintPath();
    boolean result = mazePrintPath.solveMaze(maze, 0, 0, 5, 5, "");
    System.out.println(result);
  }

  public boolean solveMaze(char[][] maze, int startX, int startY, int targetX, int targetY, String path) {
    if(startX < 0 || startX >= maze.length || startY < 0 || startY >= maze[0].length || maze[startX][startY] == 'X') {
      return false;
    }

    if(startX == targetX && startY == targetY) {
      System.out.println(path);
      return true;
    }

    maze[startX][startY] = 'X';

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    char[] direction = {'D', 'R', 'U', 'L'};

    for(int i = 0; i < 4; i++) {
      String newPath = path + direction[i] + " ";
      if(solveMaze(maze, startX + dx[i], startY + dy[i], targetX, targetY, newPath)) {
        return true;
      }
    }

    return false;
  }
}
