import java.util.Stack;

// The Gem Collector interpret the input
// and calculates the maximum number of treasures that can be obtained on the given map,
// moving from entrance to exit and prints the past route. 
// The place that once passed cannot pass again.
// The first line of the input should be in the form of 
// (y-coordinate size)(space)(x-coordinate size).
// Gem Collector recognizes 's' as the starting point, 'e' as the destination,'!' as an obstacle,
// '*' as a treasure, and '.' as a road. Everything else is considered invalid input.
// The map size should not exceed 15x15.
public class GemCollector {
  // Save the information of the given map.
  private boolean[][] gemMap;  // true if gem exists at the point.
  private boolean[][] obstacleMap; // true if obstacle exists at the point.
  private int mapSizeY = -1;
  private int mapSizeX = -1;
  private Point startPoint = new Point();
  private Point endPoint = new Point();
  private Stack<PointWithMap> dfsStack = new Stack<>();

  // Save the answer.
  private int answerGem = -1;
  private boolean[][] answerPath;

  public GemCollector(String inputPath) throws Exception {
    // Interpret the map in given file and set the variables.
    InputManager inputManager = new InputManager(this);
    inputManager.getInput(inputPath);
  }

  // Collect the gems while traveling the path 
  // and update maximum number of gems that can be collected and corresponding path.
  public int collect() {
    // Push the start point with path map to dfsStack.
    pushStartPoint();

    // Do depth first search(dfs) until the stack is empty.
    while (!dfsStack.empty()) {
      // Information(point, path map) about currently searching point.
      PointWithMap currentPointWithMap = dfsStack.pop();
      Point currentPoint = currentPointWithMap.getPoint();
      int currentY = currentPoint.getY();
      int currentX = currentPoint.getX();
      boolean[][] currentPathMap = currentPointWithMap.getPathMap();

      // Indicate that visited the current point on the map.
      currentPathMap[currentY][currentX] = true;

      // If gem exists in current point, collect it.
      if (gemMap[currentY][currentX]) {
        currentPointWithMap.collectGem();
      }

      // When end point is reached, update the answer gem and path.
      if (currentPoint.getY() == endPoint.getY() && currentPoint.getX() == endPoint.getX()) {
        updateAnswer(currentPointWithMap);
        // When you reach the destination, there is no need to search for the next point.
        continue;
      }

      // Push the adjacent points for later search to the dfs stack.
      // Possible movements of the dfs. (up, left, down, right)
      int[] dy = {-1, 0, 1, 0};
      int[] dx = {0, -1, 0, 1};

      for (int index = 0; index < 4; ++index) {
        // Coordinate of the point to explore later.
        int nextY = currentY + dy[index];
        int nextX = currentX + dx[index];

        // Out of index.
        if (nextY == -1 || nextY == mapSizeY || nextX == -1 || nextX == mapSizeX) {
          continue;
        }

        // Avoid obstacle.
        if (obstacleMap[nextY][nextX]) {
          continue;
        }
        
        // Already visited.
        if (currentPathMap[nextY][nextX]) {
          continue;
        }

        // Push the next point with path map to the stack.
        pushNextPoint(nextY, nextX, currentPointWithMap.getCollectedGem(), currentPathMap);
      }
    }

    printAnswerPath();

    return answerGem;
  }

  // If the path collected the largest number of gems, update the answer gem and path.
  public void updateAnswer(PointWithMap currentPointWithMap) {
    if (currentPointWithMap.getCollectedGem() > answerGem) {
      answerGem = currentPointWithMap.getCollectedGem();;
      for (int i = 0; i < currentPointWithMap.getPathMap().length; ++i) {
        System.arraycopy(currentPointWithMap.getPathMap()[i], 0, 
                         answerPath[i], 0, currentPointWithMap.getPathMap()[i].length);
      }
    }
  }

  // Deep copy src array to dest array.
  public void deepCopyArray(boolean[][] srcArray, boolean[][] destArray) {
    for (int i = 0; i < srcArray.length; ++i) {
      System.arraycopy(srcArray[i], 0, destArray[i], 0, srcArray[i].length);
    }
  }

  // Push startpoint to the dfs stack.
  public void pushStartPoint() {
    // Make new path map for the start point.
    boolean[][] startPathMap = new boolean[mapSizeY][mapSizeX];

    for (int i = 0; i < mapSizeY; ++i) {
      for (int j = 0; j < mapSizeX; ++j) {
        startPathMap[i][j] = false;
      }
    }

    pushNextPoint(startPoint.getY(), startPoint.getX(), 0, startPathMap);
  }

  // Push point to the stack for later search.
  public void pushNextPoint(int y, int x, int collectedGem, boolean[][] pathMap) {
    Point nextPoint = new Point(y, x);
    int nextCollectedGem = collectedGem;
    boolean[][] nextPathMap = new boolean[mapSizeY][mapSizeX];
    deepCopyArray(pathMap, nextPathMap);
    PointWithMap nextPointWithMap = new PointWithMap(nextPoint, nextPathMap, nextCollectedGem);

    dfsStack.push(nextPointWithMap);
  }

  // Using the given mapSizeY and mapSizeX, initialize gem map, obstacle map and answer path.
  // All points in maps should be initialized to false.
  public void initializeMaps() {
    gemMap = new boolean[mapSizeY][mapSizeX];
    obstacleMap = new boolean[mapSizeY][mapSizeX];
    answerPath = new boolean[mapSizeY][mapSizeX];

    for (int i = 0; i < mapSizeY; ++i) {
      for (int j = 0; j < mapSizeX; ++j) {
        gemMap[i][j] = false;
        obstacleMap[i][j] = false;
        answerPath[i][j] = false;
      }
    }
  }

  public void setStratPoint(int y, int x) {
    startPoint.setY(y);
    startPoint.setX(x);
  }

  public void setEndPoint(int y, int x) {
    endPoint.setY(y);
    endPoint.setX(x);
  }

  public int getMapSizeX() {
    return mapSizeX;
  }

  public void setMapSizeX(int mapSizeX) throws GemMapInputException {
    if (mapSizeX > 15) {
      throw new GemMapInputException("Map size should not exceed 15x15.");
    }
    this.mapSizeX = mapSizeX;
  }

  public int getMapSizeY() {
    return mapSizeY;
  }

  public void setMapSizeY(int mapSizeY) throws GemMapInputException{
    if (mapSizeY > 15) {
      throw new GemMapInputException("Map size should not exceed 15x15.");
    }
    this.mapSizeY = mapSizeY;
  }

  public void setGemMap(int y, int x, boolean flag) {
    gemMap[y][x] = flag;
  }

  public void setObstacleMap(int y, int x, boolean flag) {
    obstacleMap[y][x] = flag;
  }

  // Print the answer path and maximum gem.
  public void printAnswerPath() {
    System.out.println("--- maze ---");
    for (int i = 0; i < mapSizeY; ++i) {
      for (int j = 0; j < mapSizeX; ++j) {
        if (answerPath[i][j]) {
          System.out.print("1");
        } else {
          System.out.print(".");
        }
      }
      System.out.print("\n");
    }
    System.out.println("Maze answer: " + answerGem);
  }

}
