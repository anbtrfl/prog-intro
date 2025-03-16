package expression.exceptions;


import expression.Operand;
import expression.UnaryMinus;

public class CheckedNegate extends UnaryMinus {

    public CheckedNegate(Operand operand) {
        super(operand);
    }

    @Override
    public int evaluate(int x, int y, int z) {

        int operand = getOperand().evaluate(x, y, z);
        return check(operand);
    }

    @Override
    public int evaluate(int valueOfVariable) {
        int operand = getOperand().evaluate(valueOfVariable);
        return check(operand);
    }

    private int check(int operand) {
        if (operand == Integer.MIN_VALUE) {
            throw new OverflowException("overflow negate ");
        }

        return -operand;
    }

}