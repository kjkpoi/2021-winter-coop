import java.util.Stack;

// Calculate the saved arithemetic expression.
public class ProfixNotationSolver {
  private String arithmeticExpression;
  private int expressionLength;
  private Stack<Double> terms = new Stack<>();

  // Initialize the arithmetic expression to solve and the length of the expression.
  public ProfixNotationSolver(String arithmeticExpression) {
    this.arithmeticExpression = arithmeticExpression;
    this.expressionLength = arithmeticExpression.length();
  }

  // Calculate the expression and then return the answer of it.
  public double calculate() throws ProfixNotationInputException {
    // Calculate in order from the front.
    // After CalculateSteps() is finished, 
    //there should be only one correct answer term in the terms stack.
    calculateSteps();
    
    double answer = terms.pop();
    if (!terms.empty()) {
      throw new ProfixNotationInputException("Lack of operator.");
    }
    
    return answer;
  }

  // Calculate the arithemetic expression in order from the front.
  // For every atomic expression, judge its kind(digit or operation or invalid input).
  // Depending on its type, each performs a different process.
  // 0. digit: push the term in the stack.
  // 1. operator: do the operation and push the answer term in the stack.
  // 2. else: throw an exception.
  public void calculateSteps() throws ProfixNotationInputException {
    for (int i = 0; i < expressionLength; ++i) {
      char term = arithmeticExpression.charAt(i);
      int termType = judgeType(term);

      switch (termType) {
        case 0:
          pushDigit(term);
          break;
        case 1:
          // There should be two operands to do the operation.
          // If not, throw an profix notation input exception.
          double operand2 = 0.0;
          double operand1 = 0.0;
          if (!terms.empty()) {
            operand2 = terms.pop();
            if (!terms.empty()) {
              operand1 = terms.pop();
            } else {
              throw new ProfixNotationInputException("Need two operands to calculate: invalid expression.");
            }
          } else {
            throw new ProfixNotationInputException("There is no operand to calculate: invalid expression.");
          }
          operate(term, operand1, operand2);
          break;
        case 2:
          throw new ProfixNotationInputException("Terms should be either number or operators: wrong input.");
        default:
          assert(false);
      }
    }
    return;
  }

  // Judge the character's type(digit, operator or invalid input).
  public int judgeType (char term) {
    if (Character.isDigit(term)) {
      return 0;
    } else if ((term == '+') || (term == '-') || (term == '*') || (term == '/')) {
      return 1;
    } else {
      return 2;
    }
  }

  // After changing the digit term to double, push it to term stack.
  public void pushDigit(char term) {
    double doubleTerm = (double) Character.digit(term, 10);
    terms.push(doubleTerm);
  }

  // Identify the operator and calculate depending on it.
  public void operate(char term, double operand1, double operand2) 
                                                            throws ProfixNotationInputException {
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
        if (operand2 == 0.0) {
          throw new ProfixNotationInputException("Divide by zero: cannot resolve the expression.");
        }
        result = operand1 / operand2;
        break;
      default:
        assert(false);
    }

    terms.push(result);
    return;
  }
}
