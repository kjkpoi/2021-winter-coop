import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// Analyze the input file and save the information in useful format.
public class InputManager {
  private PointInfo[][] pointInfoMap;
  private int mapSizeY = -1;
  private int mapSizeX = -1;
  private Point startPoint = new Point();
  private Point endPoint = new Point();

  // For a given file, analyze the file.
  public void getInput(String filename) throws Exception {
    // Read file with FileInputStream.
    FileInputStream inputStream = null;
    try {
      inputStream = new FileInputStream(filename);
      BufferedReader inFile = new BufferedReader(new InputStreamReader(inputStream));
      String nextLine = null;

      // Set map size.
      nextLine = inFile.readLine();
      mapSizeY = Character.getNumericValue(nextLine.charAt(0));
      mapSizeX = Character.getNumericValue(nextLine.charAt(2));

      // Set point information map with given size.
      pointInfoMap = new PointInfo[mapSizeY][mapSizeX];
      for (int i = 0; i < mapSizeY; i++) {
        for (int j = 0; j < mapSizeX; j++) {
          pointInfoMap[i][j] = new PointInfo();
        }
      }
      
      // For guaranting one start point and one end point.
      boolean isStartPointExists = false;
      boolean isEndPointExists = false;

      // Read map line by line.
      for (int i = 0; i < mapSizeY; ++i) {
        nextLine = inFile.readLine();
        if (nextLine == null) {
          inFile.close();
          throw new GemMapInputException("The y-coordinator length of the map is smaller than the given size.");
        }

        // Each line should be no smaller than map's x-coordinate length.
        // If the length of the line is larger, ignore the exceeding part and keep going.
        if (nextLine.length() < mapSizeX) {
          inFile.close();
          throw new GemMapInputException("The x-coordinator length of the map is smaller than the given size.");
        } else if (nextLine.length() > mapSizeX) {
          System.out.println("The x-coordinator length of the map is larger than the given size. The program will ignore the information outside the map.");
        }

        // Save the obstacle and gem information of each point in the point information map.
        for (int j = 0; j < mapSizeX; ++j) {
          char input = nextLine.charAt(j);
          switch (input) {
            case '!':
              pointInfoMap[i][j].setObstacle(true);
              break;
            case '*':
              pointInfoMap[i][j].setGem(true);
              break;
            case 's':
              if (!isStartPointExists) {
                isStartPointExists = true;
                startPoint.setX(j);
                startPoint.setY(i);
              } else {
                inFile.close();
                throw new GemMapInputException("Two start points exist. There should be one start point.");
              }
              break;
            case 'e':
              if (!isEndPointExists) {
                isEndPointExists = true;
                endPoint.setX(j);
                endPoint.setY(i);
              } else {
                inFile.close();
                throw new GemMapInputException("Two end points exist. There should be one end point.");
              }
              break;
            case '.':
              break;
            default:
              inFile.close();
              throw new GemMapInputException("Invalid symbol in the map.");
          }
        }
      }

      // Throw an exception if there is no start point or end point in the map.
      if (!isStartPointExists) {
        inFile.close();
        throw new GemMapInputException("There is no start point in the map.");
      }
      if (!isEndPointExists) {
        inFile.close();
        throw new GemMapInputException("There is no start point in the map.");
      }

      inFile.close();

    } catch (IOException e) {
      System.out.println("No such file.");
    }
  }

  public Point getStartPoint() {
    return startPoint;
  }

  public Point getEndPoint() {
    return endPoint;
  }

  public PointInfo[][] getPointInfoMap() {
    return pointInfoMap;
  }
  
  public int getMapSizeX() {
    return mapSizeX;
  }

  public int getMapSizeY() {
    return mapSizeY;
  }
}
