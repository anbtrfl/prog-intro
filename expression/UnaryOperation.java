package expression;


import java.util.Map;
import java.util.Objects;

public abstract class UnaryOperation implements Expression, TripleExpression {

    Operand operand;
    String sign;
    int level;
    boolean isDouble;
    Map<String, Integer> signToLevel = Map.of(
            "-", 1
    );

    public UnaryOperation(Operand operand) {
        this.operand = operand;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        //System.out.println(" m");
        return sign + "(" +  operand.toString() + ")";

    }

//    public String toMiniString() {
//        String oper;
//
//        if (operand.getLevel() < level) {
//            oper = operand.toString();
//        } else {
//            oper = operand.toMiniString();
//        }
//
//        return sign + " " + oper;
//    }

    public void fillVariable(int parameterX, int parameterY, int parameterZ) {
        operand.evaluate(parameterX, parameterY, parameterZ);
    }

    public void fillVariable(int parameter) {
        operand.evaluate(parameter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnaryOperation that = (UnaryOperation) o;
        return isDouble == that.isDouble &&
                Objects.equals(operand, that.operand) &&
                Objects.equals(sign, that.sign);
    }
    public Operand getOperand() {
        return operand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, sign, isDouble, signToLevel);
    }

}
