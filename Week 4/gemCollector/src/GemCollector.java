import java.util.Stack;

public class GemCollector {
  // Save the information of the given map.
  private PointInfo[][] pointInfoMap;
  private int mapSizeY;
  private int mapSizeX;
  private Point startPoint;
  private Point endPoint;
  private Stack<PointWithMap> dfsStack = new Stack<>();

  // Save the answer.
  int answerGem = -1;
  boolean answerPath[][];

  // Initialize the information of the given map.
  public GemCollector(PointInfo[][] pointInfoMap, Point startPoint, Point endPoint) {
    this.mapSizeY = pointInfoMap.length;
    this.mapSizeX = pointInfoMap[0].length;
    this.pointInfoMap = pointInfoMap;
    this.startPoint = startPoint;
    this.endPoint = endPoint;

    this.answerPath = new boolean[mapSizeY][mapSizeX];
  }

  // Find the maxim gem and the answer path.
  public int collect() {

    // Push the start point to dfsStack.
    // Initialize every element of the path map of the start point to false.
    pushStartPoint();

    // Do depth first search(dfs) until the stack is empty.
    while(!dfsStack.empty()) {
      // Information(point, path map) about currently searching point.
      PointWithMap currentPointWithMap = dfsStack.pop();
      Point currentPoint = currentPointWithMap.getPoint();
      int currentY = currentPoint.getY();
      int currentX = currentPoint.getX();
      boolean[][] currentPathMap = currentPointWithMap.getPathMap();

      // Indicate that visited the current point on the map.
      currentPathMap[currentY][currentX] = true;

      // If gem exists in current point, collect it.
      if (pointInfoMap[currentY][currentX].getGem()) {
        currentPointWithMap.collectGem();
      }

      // When end point is reached, update the answer gem and path.
      if (currentPoint.getY() == endPoint.getY() && currentPoint.getX() == endPoint.getX()) {
        updateAnswer(currentPointWithMap);
        continue;
      }

      // Push the adjacent points for later search to the dfs stack.
      // Possible movements of the dfs. (up, left, down, right)
      int dy[] = {-1, 0, 1, 0};
      int dx[] = {0, -1, 0, 1};

      for(int index = 0; index < 4; ++index) {
        // adjacent point's coordianate.
        int nextY = currentY + dy[index];
        int nextX = currentX + dx[index];

        // Out of index.
        if (nextY == -1 || nextY == mapSizeY || nextX == -1 || nextX == mapSizeX) {
            continue;
        }

        // Avoid obstacle.
        if (pointInfoMap[nextY][nextX].getObstacle()){
            continue;
        }
        
        // Already visited.
        if (currentPathMap[nextY][nextX]){
            continue;
        }

        // Make point with map (corresponding to next point) and push it to the stack.
        pushNextPoint(nextY, nextX, currentPointWithMap.getCollectedGem(), currentPathMap);
      }
    }

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
    // nextPathMap = pathMap;
    deepCopyArray(pathMap, nextPathMap);
    PointWithMap nextPointWithMap = new PointWithMap(nextPoint, nextPathMap, nextCollectedGem);

    dfsStack.push(nextPointWithMap);
  }

  public boolean[][] getAnswerPath() {
    return answerPath;
  }

}
