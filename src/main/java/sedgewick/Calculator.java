package sedgewick;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {

    private Deque<Double> operands = new ArrayDeque<>();
    private Deque<Character> operators = new ArrayDeque<>();

    private static double arithmeticOperation(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
    }

    public double calculate(String expression) {
        for (Character symbol : expression.toCharArray()) {
            switch (symbol) {
                case '(':
                    break;
                case '+':
                    operators.push(symbol);
                    break;
                case '*':
                    operators.push(symbol);
                    break;
                case '/':
                    operators.push(symbol);
                    break;
                case ')':
                    double second = operands.pop();
                    double first = operands.pop();
                    char operator = operators.pop();
                    double result = arithmeticOperation(first, second, operator);
                    operands.push(result);
                    break;
                default:
                    operands.push(Double.parseDouble(symbol.toString()));
            }

        }

        return operands.pop();
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        double result = calc.calculate("(((1+((2+3)*(4*5)))/9)*5)");
        System.out.println(result);
    }
}
