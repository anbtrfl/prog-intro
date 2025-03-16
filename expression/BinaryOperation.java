package expression;

import java.util.Map;
import java.util.Objects;

public abstract class BinaryOperation implements Operand, Expression, TripleExpression {
    Operand operand1;
    Operand operand2;
    String sign;
    int level;
    boolean isDouble;
    Map<String, Integer> signToLevel = Map.of(
            "+", 2,
            "*", 3,
            "/", 3,
            "-", 2
    );



    public BinaryOperation(Operand operand1, Operand operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + " " + sign + " " + operand2.toString() + ")";
    }

//    public String toMiniString() {
//        StringBuilder test = new StringBuilder();
//
//        if (operand1.getLevel() < this.getLevel()) {
//            test.append("(").append(operand1.toMiniString()).append(")");
//        } else {
//            test.append(operand1.toMiniString());
//        }
//
//        test.append(" ").append(sign).append(" ");
//
//        if (operand2.getLevel() < this.getLevel() || (operand2.getLevel() == this.getLevel() && (
//                operand2.leftAssociativity() || this.rightAssociativity()))) {
//            test.append("(").append(operand2.toMiniString()).append(")");
//        } else {
//            test.append(operand2.toMiniString());
//        }
//
//        return test.toString();
//    }

//    public void fillVariable(double parameterX, double parameterY, double parameterZ) {
//        operand1.evaluate(parameterX, parameterY, parameterZ);
//        operand2.evaluate(parameterX, parameterY, parameterZ);
//    }

    public void fillVariable(int parameterX, int parameterY, int parameterZ) {
        operand1.evaluate(parameterX, parameterY, parameterZ);
        operand2.evaluate(parameterX, parameterY, parameterZ);
    }


    public void fillVariable(int parameter) {
        operand1.evaluate(parameter);
        operand2.evaluate(parameter);
    }

    public Operand getOperand1() {
        return operand1;
    }

    public Operand getOperand2() {
        return operand2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BinaryOperation that = (BinaryOperation) o;

        return isDouble == that.isDouble &&
                Objects.equals(operand1, that.operand1) &&
                Objects.equals(operand2, that.operand2) &&
                Objects.equals(sign, that.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand1, operand2, sign, isDouble, signToLevel);
    }
}

