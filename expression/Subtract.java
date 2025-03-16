package expression;
import expression.exceptions.EvaluateException;

public class Subtract extends BinaryOperation {
    public Subtract(Operand operand1, Operand operand2) {
        super(operand1, operand2);
        this.sign = "-";
        this.level = signToLevel.get(sign);
    }

    @Override
    public int getValue() {
        return operand1.getValue() - operand2.getValue();

    }

//    @Override
//    public double getDValue() {
//        return operand1.getDValue() - operand2.getDValue();
//    }

    @Override
    public int evaluate(int parameter) {
        super.fillVariable(parameter);
        return getValue();
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
    public int evaluate(int parameterX, int parameterY, int parameterZ) throws EvaluateException{
        super.fillVariable(parameterX, parameterY, parameterZ);
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean rightAssociativity() {
        return true;
    }
}
