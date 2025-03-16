package expression;

import expression.exceptions.EvaluateException;

public class UnaryMinus extends UnaryOperation implements Operand {

    public UnaryMinus(Operand operand) {
        super(operand);
        this.sign = "-";
        this.level = signToLevel.get(sign);
        //System.out.println("unaryMinus operand: " + operand.toString());
    }

//    @Override
//    public double evaluate(double parameter) {
//        super.fillVariable(parameter);
//        return getDValue();
//    }
//
//    @Override
//    public double evaluate(double parameterX, double parameterY, double parameterZ) {
//        super.fillVariable(parameterX, parameterY, parameterZ);
//        return getDValue();
//    }

    @Override
    public int getValue() {
        return - operand.getValue();
    }

//    @Override
//    public double getDValue() {
//        return - operand.getDValue();
//    }

    @Override
    public int evaluate(int parameter) {
        this.fillVariable(parameter);
        return getValue();
    }

    @Override
    public int evaluate(int parameterX, int parameterY, int parameterZ) throws EvaluateException {
        this.fillVariable(parameterX, parameterY, parameterZ);
        return getValue();
    }

    @Override
    public String toString() {
        return "-" +"("+ operand.toString()+")";
    }

//    public String toMiniString() {
//        return "-" + operand.toMiniString();
//    }
}
