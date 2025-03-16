package expression;

import expression.exceptions.EvaluateException;

public class Count extends UnaryOperation implements Operand {
    public Count(Operand operand) {
        super(operand);
    }
    @Override
    public String toString() {
        return "count(" + operand.toString() + ")";
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
        String binary = Integer.toBinaryString(operand.getValue());
        int countOnes = 0;
        for (char element : binary.toCharArray()){
            if (element == '1') countOnes++;
        }
        return countOnes;
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

    @Override
    public int evaluate(int x, int y, int z) throws EvaluateException {
        super.fillVariable(x, y, z);
        return getValue();
    }
}
