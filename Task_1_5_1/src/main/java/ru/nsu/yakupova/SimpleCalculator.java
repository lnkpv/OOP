package ru.nsu.yakupova;

/**
 * Class for Simple Calculator.
 */
public class SimpleCalculator {

    /**
     * Method for choosing operator.
     */
    public static Operation useOperator(OperationType type, ComplexNumber a, ComplexNumber b) {
        Operation oper = null;

        switch (type) {
            case PLUS:
                oper = new Plus(a, b);
                break;
            case MINUS:
                oper = new Minus(a, b);
                break;
            case MULTIPLY:
                oper = new Multiply(a, b);
                break;
            case DIVIDE:
                oper = new Divide(a, b);
                break;
            case SIN:
                oper = new Sin(a);
                break;
            case COS:
                oper = new Cos(a);
                break;
            case SQRT:
                oper = new Sqrt(a);
                break;
            case LOG:
                oper = new Log(a);
                break;
            case POW:
                oper = new Pow(a, b);
                break;
            default:
                break;
        }

        return oper;
    }
}