import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class ProfixNoationSolverTest {
  @Test
  public void noOperandTest() throws Exception {
    String arithmeticExpression = "+";
    ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver(arithmeticExpression);
    ProfixNotationInputException profixNotationInputException = 
        assertThrows(ProfixNotationInputException.class, () -> profixNotationSolver.calculate());
    String message = profixNotationInputException.getMessage();
    assertEquals("There is no operand to calculate: invalid expression.", message);
  }

  @Test
  public void oneOperandTest() throws Exception {
    String arithmeticExpression = "7+";
    ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver(arithmeticExpression);
    ProfixNotationInputException profixNotationInputException = 
        assertThrows(ProfixNotationInputException.class, () -> profixNotationSolver.calculate());
    String message = profixNotationInputException.getMessage();
    assertEquals("Need two operands to calculate: invalid expression.", message);
  }

  @Test
  public void invalidInputTest() throws Exception {
    String arithmeticExpression = "76+3!";
    ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver(arithmeticExpression);
    ProfixNotationInputException profixNotationInputException = 
        assertThrows(ProfixNotationInputException.class, () -> profixNotationSolver.calculate());
    String message = profixNotationInputException.getMessage();
    assertEquals("Terms should be either number or operators: wrong input.", message);
  }

  @Test
  public void divideByZeroTest() throws Exception {
    String arithmeticExpression = "70/";
    ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver(arithmeticExpression);
    ProfixNotationInputException profixNotationInputException = 
        assertThrows(ProfixNotationInputException.class, () -> profixNotationSolver.calculate());
    String message = profixNotationInputException.getMessage();
    assertEquals("Divide by zero: cannot resolve the expression.", message);
  }

  @Test
  public void lackOfOperatorTest() throws Exception {
    String arithmeticExpression = "73+2";
    ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver(arithmeticExpression);
    ProfixNotationInputException profixNotationInputException = 
        assertThrows(ProfixNotationInputException.class, () -> profixNotationSolver.calculate());
    String message = profixNotationInputException.getMessage();
    assertEquals("Lack of operator.", message);
  }

  @Test
  public void answerTest() throws Exception {
    String[] arithmeticExpressions = {"89+53-*4/", "23*4+23+*", "345+*", "12+34+*7/"};
    double[] answers = {8.5, 50, 27, 3};

    for (int i = 0; i < 4; ++i) {
      ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver(arithmeticExpressions[i]);
      double solverAnswer = profixNotationSolver.calculate();
      assertEquals(answers[i], solverAnswer);
    }
  }

}
