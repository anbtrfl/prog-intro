package expression;

import expression.exceptions.EvaluateException;

public class Clear extends BinaryOperation  implements Operand{
    public Clear (Operand operand1, Operand operand2) {
        super(operand1, operand2);
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + " " + "clear" + " " + operand2.toString() + ")";
    }

    @Override
    public int evaluate(int x, int y, int z) throws EvaluateException {
        super.fillVariable(x, y, z);
        return getValue();
    }

//    @Override
//    public double evaluate(double x) {
//        return 0;
//    }

//    @Override
//    public double evaluate(double parameterX, double parameterY, double parameterZ) {
//        return 0;
//    }

    @Override
    public int getValue() {
//        String binary = Integer.toBinaryString(operand1.getValue());
//
//        char ch = '0';
//        int pos = binary.length()-1-operand2.getValue();
//
//        binary = binary.substring(0, pos) + ch + binary.substring(pos + 1);
//        int res = Integer.parseInt(binary, 2);
//        return res;
        return operand1.getValue() & ~(1<<operand2.getValue());
    }

//    @Override
//    public double getDValue() {
//        return 0;
//    }

    @Override
    public int evaluate(int x) {
        super.fillVariable(x);
        return getValue();
    }
}