import java.util.Stack;

// Calculate one profix notation arithemetic expression with no white space.
// Operands should be natural numbers below 10.
// Operators are +, -, *, and /.
public class ProfixNotationSolver {
  private Stack<Double> stack = new Stack<>();
  private final char[] operators = {'+', '-', '*', '/'};

  // Calculate the given arithemetic expression and return the answer.
  public double calculate(String arithmeticExpression) throws Exception {
    double answer = 0;
    for (int i = 0; i < arithmeticExpression.length(); ++i) {
      char term = arithmeticExpression.charAt(i);
      if (Character.isDigit(term)) {
        stack.push((double) Character.digit(term, 10));
      } else if (isOperator(term)) {
        // Push the calculated answer to the stack.
        double operand1 = stack.pop();
        double operand2 = stack.pop();
        stack.push(operate(term, operand2, operand1));
      } else {
        throw new InvalidInputException();
      }
    }
    answer = stack.pop();
    if (!stack.empty()) {
      throw new TwoOrMoreExpressionsException();
    }
    return answer;
  }

  // Check if the term is an operator or not.
  public boolean isOperator(char term) {
    for (char operator : operators) {
      if (term == operator) {
        return true;
      }
    }
    return false;
  }

  // Identify the operator and return the calculated answer depending on it.
  public double operate(char term, double operand1, double operand2) {
    double result = 0.0;
    switch (term) {
      case '+':
        result = operand1 + operand2;
        break;
      case '-':
        result = operand1 - operand2;
        break;
      case '*':
        result = operand1 * operand2;
        break;
      case '/':
        result = operand1 / operand2;
        break;
      default:
        assert (false);
    }
    return result;
  }
}
