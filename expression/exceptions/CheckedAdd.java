package expression.exceptions;

import expression.Add;
import expression.Operand;

public class CheckedAdd extends Add {
    public CheckedAdd(Operand operand1, Operand operand2) {
        super(operand1, operand2);
    }

    @Override
    public int evaluate(int parameterX, int parameterY, int parameterZ) {
        int operand2 = getOperand2().evaluate(parameterX,  parameterY, parameterZ);
        int operand1 = getOperand1().evaluate(parameterX,  parameterY, parameterZ);
        return check(operand1, operand2);
    }

    public int evaluate(int parameter) {
        int operand2 = getOperand2().evaluate(parameter);
        int operand1 = getOperand1().evaluate(parameter);
        return check(operand1, operand2);
    }

    private int check(int operand1, int operand2) {
        int result = operand1 + operand2;
        if (operand1 >= 0 && operand2 >= 0 && result < 0) {
            throw new OverflowException("overflow сложение 1");
        } else if (operand1 < 0 && operand2 < 0 && result >= 0) {
            throw new OverflowException("overflow сложение 2");
        }
        return result;
    }
}