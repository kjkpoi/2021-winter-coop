import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// Schedule the jobs in the given file and prints the log.
// The scheduler selects tasks using priority queue.
// Information on job priority is contained in Job class.
public class Scheduler {
  private PriorityQueue<Job> jobPriorityQueue = new PriorityQueue<>();
  private int time = 0;
  private int[] endtime; // An array to save the end time of jobs.

  public Scheduler(String inputPath) throws Exception {
    setJobPriorityQueue(inputPath);
  }

  // Read the input file and add the jobs in the priority queue one by one.
  public void setJobPriorityQueue(String inputPath) throws Exception {
    FileInputStream inputStream = null;
    inputStream = new FileInputStream(inputPath);
    BufferedReader inFile = new BufferedReader(new InputStreamReader(inputStream));

    // Get the number of jobs.
    int numberOfJobs = Integer.parseInt(inFile.readLine());

    // Initialize the endtime array.
    endtime = new int[numberOfJobs];

    // Insert jobs in the input file in the priority queue.
    insertJobs(numberOfJobs, inFile);
  }

  // Read job information in the input file and make a new job instance.
  // Put the job instance in the priority queue.
  public void insertJobs(int numberOfJobs, BufferedReader inFile) throws Exception{
    for (int i = 0; i < numberOfJobs; ++i) {
      String nextLine = inFile.readLine();
      if (nextLine == null) {
        throw new WrongInputException(
            "The actual number of jobs does not match the declared number of jobs.");
      }

      String[] jobInfo = nextLine.split("\\s");

      if (jobInfo.length > 3) {
        throw new WrongInputException("Job ID " + (i + 1) 
            + ": Wrong job information format in input file.");
      }
      try {
        int priority = Integer.parseInt(jobInfo[0]);
        int unit = Integer.parseInt(jobInfo[1]);
        int remainingTimeLength = Integer.parseInt(jobInfo[2]);

        // Insert a created job in the priority queue.
        Job job = new Job(i + 1, priority, unit, remainingTimeLength);
        jobPriorityQueue.add(job);

      } catch (NumberFormatException e) {
        throw new WrongInputException(
          "Wrong input file format: Job information must be expressed as an integer.");
      }
    }
  }

  // Schedule jobs in the job priority queue.
  // The scheduler pulls out a task from the priority queue according to the priority of the task, 
  // allocates it, and puts it back into the priority queue when the task is remaining.
  public int[] schedule() {
    while (!jobPriorityQueue.isEmpty()) {
      // Increase time.
      time += 1;

      // Find the task to be executed.
      Job currentJob = jobPriorityQueue.poll();

      // Decrease remaining length of the job by 1.
      currentJob.decreaseRemainingLength();

      // Print shedule log.
      System.out.println(time + ": " + currentJob.toString());

      // If the job is finished, save end time of the job.
      // If not, insert it in the job priority queue.
      if (currentJob.getRemainingTimeLength() == 0) {
        endtime[currentJob.getId() - 1] = time;
      } else {
        currentJob.increasePriorityByUnit();
        jobPriorityQueue.add(currentJob);
      }
    }

    return endtime;
  }
}
