import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.EmptyStackException;
import org.junit.Test;

public class ProfixNoationSolverTest {
  @Test
  public void noOperandTest() throws Exception {
    String arithmeticExpression = "+";
    ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver();
    assertThrows(EmptyStackException.class, 
        () -> profixNotationSolver.calculate(arithmeticExpression));
  }

  @Test
  public void invalidInputTest() throws Exception {
    String arithmeticExpression = "76+3!";
    ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver();
    assertThrows(InvalidInputException.class, 
        () -> profixNotationSolver.calculate(arithmeticExpression));
  }

  @Test
  public void twoExpressionTest() throws Exception {
    String arithmeticExpression = "34+2";
    ProfixNotationSolver profixNotationSolver = new ProfixNotationSolver();
    assertThrows(TwoOrMoreExpressionsException.class, 
        () -> profixNotationSolver.calculate(arithmeticExpression));
  }

  @Test
  public void answerTest() throws Exception {
    String[] arithmeticExpressions = {"89+53-*4/", "23*4+23+*", "345+*", "12+34+*7/"};
    double[] answers = {8.5, 50, 27, 3};

    for (int i = 0; i < 4; ++i) {
      ProfixNotationSolver profixNotationSolver =
          new ProfixNotationSolver();
      double solverAnswer = profixNotationSolver.calculate(arithmeticExpressions[i]);
      assertEquals(answers[i], solverAnswer);
    }
  }

}
