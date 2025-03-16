package expression;

import java.util.Objects;

public class Variable implements Operand {

    private final String variable;
    //private double value;
    private int value;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public int getValue() {
        return (int) value;
    }

    @Override
    public int getLevel() {
        return 5;
    }

//    @Override
//    public double getDValue() {
//        return value;
//    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public int evaluate(int parameter) {
        this.value = parameter;
        return getValue();
    }

//    @Override
//    public double evaluate(double parameter) {
//        this.value = parameter;
//        return getDValue();
//    }

//    @Override
//    public String toMiniString() {
//        return variable;
//    }

//    @Override
//    public double evaluate(double parameterX, double parameterY, double parameterZ) {
//        if (Objects.equals(variable, "x")) {
//            this.value = parameterX;
//        }
//        if (Objects.equals(variable, "y")) {
//            this.value = parameterY;
//        }
//        if (Objects.equals(variable, "z")) {
//            this.value = parameterZ;
//        }
//        return getDValue();
//    }

    @Override
    public int evaluate(int parameterX, int parameterY, int parameterZ) {
        if (Objects.equals(variable, "x")) {
            this.value = parameterX;
        }
        if (Objects.equals(variable, "y")) {
            this.value = parameterY;
        }
        if (Objects.equals(variable, "z")) {
            this.value = parameterZ;
        }
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable that = (Variable) o;
        return Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable);
    }
}
