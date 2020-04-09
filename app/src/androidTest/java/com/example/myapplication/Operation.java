package com.example.myapplication;

import java.util.ArrayList;

public class Operation {

    final static private double INTEGRAL_STEPS = 10000;
    final static private double DERIVATIVE_LEN = .00000001;

    /**
     * Returns an array of the last numInput values of the array list and removes
     * the operation from the array list
     * @param numInputs the number of inputs for an operation
     * @param formula the array list from which values are taken
     * @return a double array of the last numInput values of the array list
     * with the values in the same order as the array list
     */
    private static double[] inputs(int numInputs, ArrayList<String> formula, int index) {
        double[] ret = new double[numInputs];
        for (int i=0; i<numInputs; i++)
            ret[i] = Double.parseDouble(formula.remove(index+1));
        formula.remove(index);
        return ret;
    }

    /**
     * integrates a comma separated expression with respect to a variable
     * @param formula
     * @param index
     */
    public static void integrate(ArrayList<String> formula, int index) {
        //initialization
        String integrand = formula.remove(index + 1).replaceAll(",", " ");
        String variable = formula.remove(index + 1);
        double[] bounds = inputs(2, formula, index);
        Expression integrandExpression = new Expression(integrand);
        double add = 0;
        double stepSize = (bounds[1]-bounds[0])/INTEGRAL_STEPS;

        //boundary divergence check


        //integration steps; trapezoid rule
        for (double x = bounds[0]; x+stepSize<bounds[1]; x += stepSize) {
            Expression left = integrandExpression.clone();
            left.setVariable(variable, x);
            Expression right = integrandExpression.clone();
            right.setVariable(variable, x+stepSize);
            add += (left.value()+right.value())/2 * stepSize;
        }
        formula.add(index, Double.toString(add));
    }

    public static void differentiate(ArrayList<String> formula, int index) {
        String function = formula.remove(index + 1).replaceAll(",", " ");
        String variable = formula.remove(index + 1);
        double[] value = inputs(1, formula, index);
        Expression functionExpression = new Expression(function);
        Expression left = functionExpression.clone();
        Expression right = functionExpression.clone();
        functionExpression.setVariable(variable, value[0]);
        left.setVariable(variable, value[0]-DERIVATIVE_LEN/2);
        right.setVariable(variable, value[0]+DERIVATIVE_LEN/2);
        double add = (right.value()-left.value())/DERIVATIVE_LEN;
        formula.add(index, Double.toString(add));
    }

    public static void add(ArrayList<String> formula, int index){
        double[] inputs = inputs(2, formula, index);
        double add = inputs[0]+inputs[1];
        formula.add(index, Double.toString(add));
    }

    public static void subtract(ArrayList<String> formula, int index){
        double[] inputs = inputs(2, formula, index);
        double add = inputs[0]-inputs[1];
        formula.add(index, Double.toString(add));
    }

    public static void multiply(ArrayList<String> formula, int index){
        double[] inputs = inputs(2, formula, index);
        double add = inputs[0]*inputs[1];
        formula.add(index, Double.toString(add));
    }

    public static void divide(ArrayList<String> formula, int index){
        double[] inputs = inputs(2, formula, index);
        double add = inputs[0]/inputs[1];
        formula.add(index, Double.toString(add));
    }

    public static void power(ArrayList<String> formula, int index){
        double[] inputs = inputs(2, formula, index);
        double add = Math.pow(inputs[0],inputs[1]);
        formula.add(index, Double.toString(add));
    }

    public static void sqrt(ArrayList<String> formula, int index) {
        double[] input = inputs(1, formula, index);
        double add = Math.pow(input[0],.5);
        formula.add(index, Double.toString(add));
    }

    public static void sin(ArrayList<String> formula, int index) {
        double[] input = inputs(1, formula, index);
        double add = Math.sin(input[0]);
        formula.add(index, Double.toString(add));
    }

    public static void cos(ArrayList<String> formula, int index) {
        double[] input = inputs(1, formula, index);
        double add = Math.cos(input[0]);
        formula.add(index, Double.toString(add));
    }

    public static void tan(ArrayList<String> formula, int index) {
        double[] input = inputs(1, formula, index);
        double add = Math.tan(input[0]);
        formula.add(index, Double.toString(add));
    }

    public static void sec(ArrayList<String> formula, int index) {
        double[] input = inputs(1, formula, index);
        double add = 1/Math.cos(input[0]);
        formula.add(index, Double.toString(add));
    }

    public static void csc(ArrayList<String> formula, int index) {
        double[] input = inputs(1, formula, index);
        double add = 1/Math.sin(input[0]);
        formula.add(index, Double.toString(add));
    }

    public static void cot(ArrayList<String> formula, int index) {
        double[] input = inputs(1, formula, index);
        double add = 1/Math.tan(input[0]);
        formula.add(index, Double.toString(add));
    }

    public static void abs(ArrayList<String> formula, int index) {
        double[] input = inputs(1, formula, index);
        double add = Math.abs(input[0]);
        formula.add(index, Double.toString(add));
    }

    public static void ln(ArrayList<String> formula, int index) {
        double[] input = inputs(1, formula, index);
        double add = Math.log(input[0]);
        formula.add(index, Double.toString(add));
    }

    public static void log10(ArrayList<String> formula, int index) {
        double[] input = inputs(1, formula, index);
        double add = Math.log10(input[0]);
        formula.add(index, Double.toString(add));
    }

    public static void log(ArrayList<String> formula, int index) {
        double[] input = inputs(2, formula, index);
        double add = Math.log(input[1])/Math.log(input[0]);
        formula.add(index, Double.toString(add));
    }

}

