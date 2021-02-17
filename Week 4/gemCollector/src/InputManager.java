import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// Analyze the input file and save the information in useful format.
public class InputManager {
  // Information of the given map.
  private GemCollector gemCollector;

  // For guaranting one start point and one end point.
  private boolean isStartPointExists = false;
  private boolean isEndPointExists = false;

  public InputManager(GemCollector gemCollector) {
    this.gemCollector = gemCollector;
  }

  // For a given file, analyze the file.
  public void getInput(String filename) throws Exception {
    // Read file with FileInputStream.
    FileInputStream inputStream = null;
    try {
      inputStream = new FileInputStream(filename);
      BufferedReader inFile = new BufferedReader(new InputStreamReader(inputStream));

      // Get map size from the first line of input and set map size x and y.
      // Initialize gem map and obstacle map depending on the input map size.
      setMapSize(inFile);

      // Set information of gems and obstacles by reading the map line by line.
      setMapInfo(inFile);

      inFile.close();

    } catch (IOException e) {
      System.out.println("No such file.");
    }
  }

  // The first line of the input contains the information of the map size.
  // Get the information and set the size and initialize gem and obstacle map to false.
  private void setMapSize(BufferedReader inFile) throws Exception {
    // Set map size.
    String nextLine = inFile.readLine();
    String[] sizeStrings = nextLine.split("\\s");
    gemCollector.setMapSizeY(Integer.parseInt(sizeStrings[0]));
    gemCollector.setMapSizeX(Integer.parseInt(sizeStrings[1]));

    // Initialize gem map and obstacle map to false.
    gemCollector.initializeMaps();
  }

  public void setMapInfo(BufferedReader inFile) throws Exception {
    String nextLine;

    // Read the map line by line.
    for (int i = 0; i < gemCollector.getMapSizeY(); ++i) {
      nextLine = inFile.readLine();

      checkNextLineSize(inFile, nextLine);

      // Save the obstacle and gem information of each point in the point information map.
      for (int j = 0; j < gemCollector.getMapSizeX(); ++j) {
        char input = nextLine.charAt(j);
        switch (input) {
          case '!':
            gemCollector.setObstacleMap(i, j, true);
            break;
          case '*':
            gemCollector.setGemMap(i, j, true);
            break;
          case 's':
            if (!setStartPoint(i, j)) {
              inFile.close();
              throw new GemMapInputException(
                  "Two start points exist. There should be one start point.");
            }
            break;
          case 'e':
            if (!setEndPoint(i, j)) {
              inFile.close();
              throw new GemMapInputException(
                  "Two end points exist. There should be one end point.");
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
      throw new GemMapInputException("There is no start point in the map.");
    } else if (!isEndPointExists) {
      throw new GemMapInputException("There is no start point in the map.");
    }

    inFile.close();
  }

  public void checkNextLineSize(BufferedReader inFile, String nextLine) throws Exception {
    // y-coordinator length should be match with the map size 
    // given in the first line of the input file.
    if (nextLine == null) {
      inFile.close();
      throw new GemMapInputException(
          "The y-coordinator length of the map is smaller than the given size.");
    }

    // Each line should be match with the map's x-coordinate size
    // given in the first line of the input file.
    if (nextLine.length() != gemCollector.getMapSizeX()) {
      inFile.close();
      throw new GemMapInputException(
          "The x-coordinator length of the map does not match the given size.");
    }
  }

  public boolean setStartPoint(int y, int x) {
    if (!isStartPointExists) {
      isStartPointExists = true;
      gemCollector.setStratPoint(y, x);
      return true;
    } else {
      return false;
    }
  }

  public boolean setEndPoint(int y, int x){
    if (!isEndPointExists) {
      isEndPointExists = true;
      gemCollector.setEndPoint(y, x);
      return true;
    } else {
      return false;
    }
  }

}
