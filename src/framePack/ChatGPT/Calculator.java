package framePack.ChatGPT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Calculator {
    private JFrame frame;
    private JTextField textField;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 50));
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };
        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.addActionListener(new ButtonClickListener());
            buttonPanel.add(btn);
        }
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            String currentText = textField.getText();

            if (command.equals("=")) {
                try {
                    double result = evaluateExpression(currentText);
                    textField.setText(String.valueOf(result));
                } catch (Exception e) {
                    textField.setText("Error");
                }
            } else {
                textField.setText(currentText + command);
            }
        }

        private double evaluateExpression(String expression) {
            try {
                return new ExpressionEvaluator().evaluate(expression);
            } catch (Exception e) {
                throw new RuntimeException("Invalid expression");
            }
        }
    }

    private class ExpressionEvaluator {
        public double evaluate(String expression) {
            expression = expression.replaceAll("\\s", ""); // Remove whitespace

            // Stacks to hold operands and operators
            Stack<Double> operandStack = new Stack<>();
            Stack<Character> operatorStack = new Stack<>();

            // Operator precedence
            int[] precedence = new int[256];
            precedence['+'] = precedence['-'] = 1;
            precedence['*'] = precedence['/'] = 2;

            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);

                // Process operand
                if (Character.isDigit(ch)) {
                    StringBuilder operand = new StringBuilder();
                    operand.append(ch);

                    // Read the remaining digits of the operand
                    while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
                        operand.append(expression.charAt(++i));
                    }

                    operandStack.push(Double.parseDouble(operand.toString()));
                }
                // Process operator
                else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    // Remove operators with higher or equal precedence from the stack and evaluate them
                    while (!operatorStack.isEmpty() && precedence[operatorStack.peek()] >= precedence[ch]) {
                        evaluateOperator(operandStack, operatorStack);
                    }

                    operatorStack.push(ch);
                }
            }

            // Evaluate any remaining operators
            while (!operatorStack.isEmpty()) {
                evaluateOperator(operandStack, operatorStack);
            }

            // The final result will be the top of the operand stack
            return operandStack.pop();
        }

        private void evaluateOperator(Stack<Double> operandStack, Stack<Character> operatorStack) {
            char operator = operatorStack.pop();
            double operand2 = operandStack.pop();
            double operand1 = operandStack.pop();
            double result;

            switch (operator) {
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
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }

            operandStack.push(result);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }
}
