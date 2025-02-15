package com.matheusvalbert.programmercalculator.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Evaluator implements Domain {

    public int evaluate(List<String> tokens) {
        List<String> postfixedTokens = infixToPostfix(tokens);
        return evaluatePostfix(postfixedTokens);
    }

    private List<String> infixToPostfix(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                output.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    private int getPrecedence(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> 0;
        };
    }

    private int evaluatePostfix(List<String> postfix) {
        Stack<Integer> stack = new Stack<>();

        for (String token : postfix) {
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
                continue;
            }
            int b = stack.pop();
            int a = stack.pop();
            int result = applyOperator(a, b, token);
            stack.push(result);
        }

        return stack.pop();
    }

    private int applyOperator(int a, int b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Unknown operator: " + op);
        };
    }
}
