import java.util.EmptyStackException;
import java.util.Scanner;

// Take input and run profix notation solver.
// And then prints the answer of the arithmetic expression.
public class App {
  public static void main(String[] args) throws Exception {
    // Get the expression to calculate.
    Scanner sc = new Scanner(System.in);
    String arithmeticExpression;

    arithmeticExpression = sc.nextLine();
    sc.close();
    
    // Make Profix Notation Solver to solve the expression 
    // and print the answer of the expression.
    ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver();
    try {
      double answer = profixNotationSolver.calculate(arithmeticExpression);
      System.out.println("Answer : " + answer);
    } catch (EmptyStackException e) {
      e.printStackTrace();
    } catch (InvalidInputException e) {
      e.printStackTrace();
    } catch (TwoOrMoreExpressionsException e) {
      e.printStackTrace();
    }
    return;
  }
}
