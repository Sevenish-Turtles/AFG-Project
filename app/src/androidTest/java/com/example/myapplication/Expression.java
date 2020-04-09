package com.example.myapplication;


import java.util.ArrayList;
import java.util.Arrays;

public class Expression {

    ArrayList<String> formula = new ArrayList<>();
    double value;

    public Expression(String expression) {
        initialize(expression);
    }

    @SuppressWarnings("unchecked")
    public Expression(ArrayList<String> formula) {
        this.formula = (ArrayList<String>) formula.clone();
    }
    @Override
    public Expression clone() {
        return new Expression(this.formula);
    }

    /**
     * Places numbers and operations from a space separated string in prefix
     * into the formula array list to use
     * @param expression A space separated string of numbers and operations which
     * represents an expression
     */
    private void initialize(String expression) {
        formula.addAll(Arrays.asList(expression.split(" ")));
        for (int i=0; i<formula.size(); i++)
            if (formula.get(i).equals("pi")) {
                formula.set(i, Double.toString(Math.PI));
            }
            else if (formula.get(i).equals("e")) {
                formula.set(i, Double.toString(Math.E));
            }
    }

    /**
     * sets all occurrences of the variable in the formula array list to the specified value
     * @param variable the variable to be replaced
     * @param value the value that the variable should be replaced with
     */
    public void setVariable(String variable, double value) {
        for (int i=0; i<formula.size(); i++)
            if (formula.get(i).equals(variable))
                formula.set(i, Double.toString(value));
    }

    /**
     * executes the expression and returns the value
     */
    public double value() {
        Expression clone = clone();
        while (clone.formula.size()>1)
            clone.operate();
        return (value = Double.parseDouble(clone.formula.get(0)));
    }

    public double getValue() {
        return value;
    }

    /**
     * performs a single operation on the formula array list
     */
    private void operate() {
        for (int i=formula.size()-1; i>-1; i--) {
            if (formula.get(i).equals("+")) {
                Operation.add(formula, i);
                break;
            }
            if (formula.get(i).equals("-")) {
                Operation.subtract(formula, i);
                break;
            }
            else if (formula.get(i).equals("*")) {
                Operation.multiply(formula, i);
                break;
            }
            else if (formula.get(i).equals("/")) {
                Operation.divide(formula, i);
                break;
            }
            else if (formula.get(i).equals("|")) {
                Operation.abs(formula, i);
                break;
            }
            else if (formula.get(i).equals("tan")) {
                Operation.tan(formula, i);
                break;
            }
            else if (formula.get(i).equals("sin")) {
                Operation.sin(formula, i);
                break;
            }
            else if (formula.get(i).equals("cos")) {
                Operation.cos(formula, i);
                break;
            }
            else if (formula.get(i).equals("sec")) {
                Operation.sec(formula, i);
                break;
            }
            else if (formula.get(i).equals("csc")) {
                Operation.csc(formula, i);
                break;
            }
            else if (formula.get(i).equals("cot")) {
                Operation.cot(formula, i);
                break;
            }
            else if (formula.get(i).equals("ln")) {
                Operation.ln(formula, i);
                break;
            }
            else if (formula.get(i).equals("log")) {
                Operation.log(formula, i);
                break;
            }
            else if (formula.get(i).equals("log10")) {
                Operation.log10(formula, i);
                break;
            }
            else if (formula.get(i).equals("^")) {
                Operation.power(formula, i);
                break;
            }
            else if (formula.get(i).equals("int")) {
                Operation.integrate(formula, i);
                break;
            }
            else if (formula.get(i).equals("der")) {
                Operation.differentiate(formula, i);
                break;
            }
            else if (formula.get(i).equals("sqrt")) {
                Operation.sqrt(formula, i);
                break;
            }
        }
    }

}
