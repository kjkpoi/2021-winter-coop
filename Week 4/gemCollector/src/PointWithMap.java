// Have information of path that has been visited while searching and the number of collected gems.
// Path map is needed to prevent searching visited point and to record the answer path.
public class PointWithMap {
  private Point point;
  private boolean[][] pathMap;
  private int collectedGem = -1;


  public PointWithMap(Point point, boolean[][] pathMap, int collectedGem) {
    this.point = point;
    this.pathMap = pathMap;
    this.collectedGem = collectedGem;
  }

  public Point getPoint() {
    return point;
  }
  
  public boolean[][] getPathMap() {
    return pathMap;
  }

  public int getCollectedGem() {
    return collectedGem;
  }

  // Increase the number of collected gem.
  public void collectGem() {
    this.collectedGem += 1;
  }
}

