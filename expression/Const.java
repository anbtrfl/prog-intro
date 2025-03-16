package expression;

import java.util.Objects;

public class Const implements Operand {
    private final int value;
    private final boolean isDouble;

    public Const(int constant) {
        this.value = constant;
        this.isDouble = false;

    }

//    public Const(double constant) {
//        this.value = constant;
//        this.isDouble = true;
//    }

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
        if (isDouble) {
            return Double.toString(value);
        } else {
            return Integer.toString((int) value);
        }
    }

    @Override
    public int evaluate(int parameter) {
        return getValue();
    }

//    @Override
//    public double evaluate(double parameter) {
//        return getDValue();
//    }

//    @Override
//    public String toMiniString() {
//        return toString();
//    }

//    @Override
//    public double evaluate(double parameterX, double parameterY, double parameterZ) {
//        return getDValue();
//    }

    @Override
    public int evaluate(int parameterX, int parameterY, int parameterZ) {
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const that = (Const) o;
        return value == that.value &&
                isDouble == that.isDouble;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, isDouble);
    }
}
