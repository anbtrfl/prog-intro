package expression;

public interface Operand extends Expression, TripleExpression{
    int getValue(); // abstract method

    int getLevel(); // abstract method

//    double getDValue(); // abstract method

//    double evaluate(double parameterX, double parameterY, double parameterZ);

    default boolean rightAssociativity() {
        return false;
    }

    default boolean leftAssociativity() {
        return false;
    }

}
