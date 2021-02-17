
public class App { 
  // Take the path of the input map
  // and make Gem Collector to calculate the largest number of gems you can collect in the map.
  public static void main(String[] args) throws Exception {
  
    String inputPath = "/Users/ykoh/Desktop/삼성SDS/개별 연구/2021-winter-coop/Week 4/gemCollector/src/testcase/test1.txt";

    try {
      GemCollector gemCollector = new GemCollector(inputPath);
      gemCollector.collect();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return;
  }
}
