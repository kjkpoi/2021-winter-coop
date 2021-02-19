import java.util.Scanner;

// Take input and run profix notation solver.
// And then prints the answer of the arithmetic expression.
public class App {
  public static void main(String[] args) throws Exception {
    // Get the expression.
    Scanner sc = new Scanner(System.in);
    String arithmeticExpression;

    arithmeticExpression = sc.nextLine();
    sc.close();
    
    // Make Profix Notation Solver to solve the expression.
    ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver(arithmeticExpression);
    double answer = profixNotationSolver.calculate();

    // Print the answer.
    System.out.println("Answer : " + answer);

    return;
  }
}
