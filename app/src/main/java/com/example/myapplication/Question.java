package com.example.myapplication;

import android.util.Log;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class Question {

    private HashMap<String,Object> vars;
    private String question,answer,variables,expressions;
    private double answerPrecision;
    private final char VAR_IDENTIFIER=':';
    final String VAREXP_DELIMETER = " ; ";
    final String PARAM_DELIMETER = ",";

    /**
     * Constructor with question, variables, expressions, answer expression,
     * and answer precision
     * @param question The question to be presented
     * @param variables String format of the variables
     * @param expressions String format of the expressions
     * @param answer The answer expression
     * @param answerPrecision double value for answer precision when checking
     */
    public Question(String question, String variables, String expressions, String answer, double answerPrecision) {
        this.variables = variables;
        this.expressions = expressions;
        this.question = question;
        this.answer = answer;
        this.answerPrecision = answerPrecision;
        this.initialize();
    }

    public Question(QuestionData q) {
        this.variables = q.getVariables();
        this.expressions = q.getExpressions();
        this.question = q.getQuestion();
        this.answer = q.getAnswer();
        this.answerPrecision = q.getAnswerPrecision();
        this.initialize();
    }

    /**
     * randomizes variables, solves for expressions, and
     * changes values in question
     */
    public void initialize() {
        vars = variables(variables);
        expressions(expressions);
        substituteVarsInQuestion(question);
    }

    /**
     * returns the question with variable values substituted
     * @return the question with variable values substituted
     */
    public String getQuestion() {
        return substituteVarsInQuestion(question);
    }

    public double getAnswer(){
        return (double) vars.get(answer);
    }

    /**
     * replaces variable names in question surrounded by VAR_IDENTIFIER
     * with the appropriate double value
     * @param question String of the question
     * @return question String with variables replaced by appropriate double values
     */
    private String substituteVarsInQuestion(String question) {
        for(String var:vars.keySet()) {
            try {
                question = question.replaceAll(VAR_IDENTIFIER+var+VAR_IDENTIFIER,
                        Double.toString((Double) vars.get(var)));
            }
            catch (ClassCastException e) {
            }
        }
        return question;
    }

    /**
     * Checks the correct answer against the user response
     * @param response user response
     * @return true if the users response is within answerPrecision
     * of the true answer, false otherwise
     */
    public  boolean checkAnswer(double response) {
        return (Math.abs(response-(double) vars.get(answer))<=answerPrecision);
    }

    /**
     * returns a HashMap of a variable name key mapped to a random double value
     * specified by a min, max, and precision value in the string
     * @param variables a string of variables with different variables separated
     * with VAREXP_DELIMETER and variables in the format "Name,Min,Max,Precision"
     * @return HashMap with randomized double variables based on specifications
     */
    private HashMap<String,Object> variables(String variables){
        String[] vars = variables.split(VAREXP_DELIMETER);
        HashMap<String,Object> ret = new HashMap<>();
        for (String var:vars) {
            String[] params = var.split(PARAM_DELIMETER);
            ret.put(params[0],
                    generateRandom(Double.parseDouble(params[1]),
                            Double.parseDouble(params[2]),
                            Double.parseDouble(params[3])));
        }
        return ret;
    }

    /**
     * appends expressions as variables in the vars hashmap
     * @param expressions the expressions separated by VAREXP_DELIMETER
     * with the expression in the form "Name,expression"
     */
    private void expressions (String expressions) {
        String[] exps = expressions.split(VAREXP_DELIMETER);
        for (String exp:exps) {
            int splitIndex = exp.indexOf(PARAM_DELIMETER);
            vars.put(exp.substring(0,splitIndex),
                    evaluateExpression(
                            exp.substring(splitIndex+1)));
        }
    }

    /**
     * evaluates the given expression
     * @param expression the given expression
     * @return the double value of the expression with the
     * vars values substitued in
     */
    public Double evaluateExpression(String expression) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
//            expression = format(expression);
                return (double) engine.eval(expression,new SimpleBindings(vars));
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Double.NaN;
        }
        catch(Exception e){
            e.printStackTrace();
            return 1.0;
        }
    }

    /**
     * generates a random double number between min and max with number of digits specified by precision
     * @param min the minimum bound on the random number
     * @param max the maximum bound on the random number
     * @param precision a double value which dictates where to truncate the random value
     * @return a random double number
     */
    public double generateRandom(double min, double max, double precision) {
        Random random = new Random();
        double value = (max-min)*random.nextDouble()+min;
        return value-=value%precision;
    }

    /**
     * formats the expression into java code
     * @param expression the expression to be formatted
     * @return the expression formatted in java code
     */
    public String format(String expression) {
        expression = expression.replaceAll("(e)", "Math.E");
        expression = expression.replaceAll("(pi)", "Math.PI");

        while(expression.contains("^")) {
            int pos = expression.indexOf('^');
            String left = expression.substring(0,pos);
            String right = expression.substring(pos+1);

            if (left.charAt(left.length()-1)==')') {
                left = left.substring(0,left.length()-1);
                int parenCounter=1;
                for (int i=left.length()-1; i>-1; i--) {
                    if (left.charAt(i)==')')
                        parenCounter++;
                    else if (left.charAt(i)=='(')
                        parenCounter--;
                    if (parenCounter==0) {
                        left = left.substring(0,i)
                                +"Math.pow("
                                +left.substring(i+1);
                        break;
                    }
                }
            }
            else {
                //return error statement
            }

            if (right.charAt(0)=='(') {
                right = right.substring(1);
                int parenCounter=1;
                for (int i=0; i<right.length(); i++) {
                    if (right.charAt(i)=='(')
                        parenCounter++;
                    else if (right.charAt(i)==')')
                        parenCounter--;
                    if (parenCounter==0) {
                        right = ","+right.substring(0,i)
                                +")"+right.substring(i+1);
                        break;
                    }
                }
            }
            else {
                //return error statement
            }

            expression = left+right;
        }
        return expression;
    }
}
