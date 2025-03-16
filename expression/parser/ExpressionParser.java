package expression.parser;

import expression.*;


public class ExpressionParser extends BaseParser implements TripleParser, TripleExpression {

    @Override
    public TripleExpression parse(String expression) {
        //System.out.println(expression);
        this.source = new StringSource(expression);
        next();
        return parseExpression();

    }

    public TripleExpression parseExpression() {
        final TripleExpression result = parseSet();
        if (!eof()) {
            error("expected end");

        }
        return result;
    }

    private TripleExpression parseSet() {
        return parseBinary('s');
    }

    private TripleExpression parseClear() {
        return parseBinary('c');
    }

    private TripleExpression parseCount() {
        return parseUnary('c');
    }

    private TripleExpression parseUnary(char operation) {
        TripleExpression operand1 = null;
        while (!eof()) {
            skipWhiteSpace();
            char op = ch;
            if (op != operation) {
                break;
            }
//
//            if (op == 'c') {
//                operand1 = new Count((Operand) operand1);
//            }
            if (take('c')) {
                expect("ount");
                operand1 = parseThird();
                operand1 = new Count((Operand) operand1);
            }
        }
        return operand1;
    }

    private TripleExpression parseFirst() {
        return parseBinary('+', '-');
    }

    private TripleExpression parseSecond() {
        return parseBinary('*', '/');
    }

    private TripleExpression parseThird() {
        skipWhiteSpace();
        if (between('0', '9')) {
            return parseConst();
        }

        if (ch == '-') {
            return parseUnaryElement();
        }

        if (ch == 'c') {
            return parseUnary('c');
        }

        if (take('(')) {
            TripleExpression lastest = parseClear();
            while (!take(')')) ;
            return lastest;
        }


        Variable variable = new Variable(String.valueOf(ch));
        next();
        return variable;

    }

    private TripleExpression parseUnaryElement() {
        TripleExpression exp;

        if (take('-')) {
            if (between('1', '9')) {
                return parseNegativeConst();
            }
            skipWhiteSpace();
            if (ch == 'c') {
                return new UnaryMinus((Operand) parseUnary('c'));
            }
            if (take('(')) {
                TripleExpression lastest = parseClear();
                while (!take(')')) ;
                return new UnaryMinus((Operand) lastest);
            } else if (ch == 'x' || ch == 'y' || ch == 'z') {
                UnaryMinus unaryMinus = new UnaryMinus(new Variable(String.valueOf(ch)));
                next();
                return unaryMinus;
            }
            exp = parseUnaryElement();
            return new UnaryMinus((Operand) exp);
        }
        return parseConst();
    }

    private TripleExpression parseBinary(char sign1, char sign2) {
        TripleExpression left = sign1 == '+' ? parseSecond() : parseThird();

        while (!eof()) {
            skipWhiteSpace();
            char sign = ch;
            if (sign != sign1 && sign != sign2) {
                break;
            }
            next();
            TripleExpression right = sign1 == '+' ? parseSecond() : parseThird();
            if (sign == '+') {
                left = new Add((Operand) left, (Operand) right);
            }
            if (sign == '-') {
                left = new Subtract((Operand) left, (Operand) right);
            }
            if (sign == '*') {
                left = new Multiply((Operand) left, (Operand) right);
            }
            if (sign == '/') {
                left = new Divide((Operand) left, (Operand) right);
            }
        }
        return left;
    }

    private TripleExpression parseBinary(char operation) {
        TripleExpression operand1 = parseFirst();

        while (!eof()) {
            skipWhiteSpace();
            char op = ch;
            if (op != 's' && op != 'c') {
                break;
            }
            next();
            TripleExpression operand2;
            if (op == 's') {
                expect("et");
                operand2 = parseFirst();
                operand1 = new Set((Operand) operand1, (Operand) operand2);
            }
            if (op == 'c') {
                expect("lear");
                operand2 = parseFirst();
                operand1 = new Clear((Operand) operand1, (Operand) operand2);
            }
//            TripleExpression operand2 = parseBit(operation);

//            if (op == 'e') {
//                operand1 = new Set((Operand) operand1, (Operand) operand2);
//            } else if (op == 'l') {
//                operand1 = new Clear((Operand) operand1, (Operand) operand2);
//            }
        }
        return operand1;
    }

//    private TripleExpression parseBit(char operation) {
//        if (operation == 's') {
//            return parseClear();
//        } else if (operation == 'c') {
//            return parseCount();
//        } else {
//            return parseFirst();
//        }
//    }

    @Override
    public String toString() {
        return TripleExpression.super.toString();
    }

    @Override
    public int evaluate(int parameterX, int parameterY, int parameterZ) {
        return 0;
    }


    private void skipWhiteSpace() {
        while (Character.isWhitespace(ch)) {
            next();
        }
    }

    private TripleExpression parseConst() {
        StringBuilder value = readAllNumber();
        return new Const(Integer.parseInt(value.toString()));
    }

    private TripleExpression parseNegativeConst() {
        StringBuilder value = readAllNumber();
        value.insert(0, '-');
        return new Const(Integer.parseInt(value.toString()));
    }

    private StringBuilder readAllNumber() {
        final StringBuilder number = new StringBuilder();
        skipWhiteSpace();
        while (between('0', '9')) {
            number.append(ch);
            next();
        }
        return number;
    }
}






