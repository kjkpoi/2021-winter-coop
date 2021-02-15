import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.EmptyStackException;
import org.junit.Test;

public class ProfixNoationSolverTest {
  int expressionIndex;

  @Test
  public void noOperandTest() throws Exception {
    String[] arithmeticExpressions = {"+", "3++++++", "23+-", "345+-**"};
    for (expressionIndex = 0; expressionIndex < arithmeticExpressions.length; expressionIndex++) {
      ProfixNotationSolverClass profixNotationSolver = new ProfixNotationSolverClass();
      assertThrows(EmptyStackException.class, 
          () -> profixNotationSolver.calculate(arithmeticExpressions[expressionIndex]));
    }
  }

  @Test
  public void invalidInputTest() throws Exception {
    String[] arithmeticExpressions = {"76+3!", "asldjoilk", "23*9sldi"};
    for (expressionIndex = 0; expressionIndex < arithmeticExpressions.length; expressionIndex++) {
      ProfixNotationSolverClass profixNotationSolver = new ProfixNotationSolverClass();
      assertThrows(InvalidInputException.class, 
          () -> profixNotationSolver.calculate(arithmeticExpressions[expressionIndex]));
    }
  }

  @Test
  public void notAnExpressionTest() throws Exception {
    String[] arithmeticExpressions = {"34+2", "23+23423+", "2342***3"};
    for (expressionIndex = 0; expressionIndex < arithmeticExpressions.length; expressionIndex++) {
      ProfixNotationSolverClass profixNotationSolver = new ProfixNotationSolverClass();
      assertThrows(NotAnExpressionException.class, 
          () -> profixNotationSolver.calculate(arithmeticExpressions[expressionIndex]));
    }
  }

  @Test
  public void answerTest() throws Exception {
    String[] arithmeticExpressions = {"89+53-*4/", "23*4+23+*", "345+*", "12+34+*7/"};
    double[] answers = {8.5, 50, 27, 3};

    for (int expressionIndex = 0; expressionIndex < 4; ++expressionIndex) {
      ProfixNotationSolverClass profixNotationSolver =
          new ProfixNotationSolverClass();
      double solverAnswer = profixNotationSolver.calculate(arithmeticExpressions[expressionIndex]);
      assertEquals(answers[expressionIndex], solverAnswer);
    }
  }

}
