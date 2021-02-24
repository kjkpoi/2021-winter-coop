public class App {
  public static void main(String[] args) {
    String inputPath = "/Users/ykoh/Desktop/삼성SDS/개별 연구/2021-winter-coop/Week 8/scheduler/src/testcase/scheduler_input1.txt";

    try {
      Scheduler scheduler = new Scheduler(inputPath);
      scheduler.schedule();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
