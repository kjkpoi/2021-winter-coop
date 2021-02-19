
public class App { 
  // Take input of the map size and map information.
  // If it is a valid input, execute Gem Collector and find the answer.
  public static void main(String[] args) throws Exception {
  
    InputManager inputManager = new InputManager();
    String currentPath = "/Users/ykoh/Desktop/삼성SDS/개별 연구/2021-winter-coop/Week 4/gemCollector/";
    inputManager.getInput(currentPath + "src/testcase/test3.txt");

    // Get map information from input manager. 
    PointInfo[][] pointInfoMap = inputManager.getPointInfoMap();
    Point startPoint = inputManager.getStartPoint();
    Point endPoint = inputManager.getEndPoint();

    // Execute Gem Collector and find the answer.
    GemCollector gemCollector = new GemCollector(pointInfoMap, startPoint, endPoint);
    int answer = gemCollector.collect();
    boolean[][] answerPath = gemCollector.getAnswerPath();

    // Print the answer.
    System.out.println("--- maze ---");
    for (int i = 0; i < inputManager.getMapSizeY(); ++i) {
      for (int j = 0; j < inputManager.getMapSizeX(); ++j) {
        if (answerPath[i][j]) {
          System.out.print("1");
        } else {
          System.out.print(".");
        }
      }
      System.out.print("\n");
    }
    System.out.println("Maze answer: " + answer);
  }

  

}
