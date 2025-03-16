package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;


public class ExpressionParser extends BaseParser implements TripleParser, TripleExpression {

    @Override
    public TripleExpression parse(String expression) throws ParseException {
        this.source = new StringSource(expression);
        next();
        return parseExpression();

    }

    public TripleExpression parseExpression() throws ParseException {
        final TripleExpression result = parseSet();
        if (ch == ')') {
//            throw new WrongBracketsException(getPos(), "No opening parenthesis");
            throw new WrongBracketsException("нет открывающей скобки");
        } else if (Character.isDigit(ch)) {
            //throw new WrongConstException(getPos(), "Spaces in numbers");
            throw new WrongBracketsException("пробелы в числе");
        } else if (!(eof())) {
            //throw new ParseException(getPos(), "End-of-input expected");
            throw new ParseException("End-of-input expected");
        }
        return result;
    }

    private TripleExpression parseSet() throws ParseException {
        return parseBinary('s');
    }

    private TripleExpression parseClear() throws ParseException {
        return parseBinary('c');
    }

    private TripleExpression parseCount() throws ParseException {
        return parseUnary('c');
    }

    private TripleExpression parseUnary(char operation) throws ParseException {
        TripleExpression operand1 = null;
            skipWhiteSpace();
//
//            if (op == 'c') {
//                operand1 = new Count((Operand) operand1);
//            }
                operand1 = parseThird();
                operand1 = new Count((Operand) operand1);

        return operand1;
    }

    private TripleExpression parseFirst() throws ParseException {
        return parseBinary('+', '-');
    }

    private TripleExpression parseSecond() throws ParseException {
        return parseBinary('*', '/');
    }

    private TripleExpression parseThird() throws ParseException {
        skipWhiteSpace();
        if (between('0', '9')) {
            return parseConst();
        }

        if (take('-')) {
            if (between('1', '9')) {
                return parseNegativeConst();
            } else {
                return parseUnaryElement();
            }

        }

//        if (ch == 'c') {
//            return parseUnary('c');
//        }
        if (Character.isLetter(ch)) {
            if (take('c')) {
                if (take('o') && take('u') && take('n') && take('t') && !Character.isLetter(ch) && !between('0', '9')) {
                    return parseUnary('c');
                } else {
                    throw new WrongCharacterException("неправильный символ");
                }

            } else {
                final StringBuilder sb = new StringBuilder();
                while (Character.isLetter(ch)) {
                    sb.append(ch);
                    next();
                }
                String variable = sb.toString();
                if (variable.equals("x") || variable.equals("y") || variable.equals("z")) {
                    return new Variable(variable);
                } else {
                    throw new WrongVariableException("bad argument.");
                }



            }
        }

        if (take('(')) {
            TripleExpression lastest = parseClear();
            skipWhiteSpace();
            if (ch != ')') {
                throw new WrongBracketsException("нет закрывающей скобки.");
            }
            next();
            return lastest;
        }


        if (ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch == 'c' || ch == 's') {
            throw new WrongOperandException("нет аргумента для операции.");
        }
        if (ch != ')') {
            throw new WrongCharacterException("непонятный символ.");
        } else {
            throw new WrongBracketsException("пустые скобки.");
        }

    }

//    private TripleExpression parseUnaryElement() throws ParseException {
//        TripleExpression exp;
//
//        if (take('-')) {
//            if (between('1', '9')) {
//                return parseNegativeConst();
//            }
//            skipWhiteSpace();
//            if (ch == 'c') {
//                return new UnaryMinus((Operand) parseUnary('c'));
//            }
//            if (take('(')) {
//                TripleExpression lastest = parseClear();
//                while (!take(')')) ;
//                return new UnaryMinus((Operand) lastest);
//            } else if (ch == 'x' || ch == 'y' || ch == 'z') {
//                UnaryMinus unaryMinus = new UnaryMinus(new Variable(String.valueOf(ch)));
//                next();
//                return unaryMinus;
//            }
//            exp = parseUnaryElement();
//            return new UnaryMinus((Operand) exp);
//        }
//        return parseConst();
//    }

    private TripleExpression parseUnaryElement() throws ParseException {
        return new CheckedNegate((Operand) parseThird());
    }

    private TripleExpression parseBinary(char sign1, char sign2) throws ParseException {
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
                left = new CheckedAdd((Operand) left, (Operand) right);
            }
            if (sign == '-') {
                left = new CheckedSubtract((Operand) left, (Operand) right);
            }
            if (sign == '*') {
                left = new CheckedMultiply((Operand) left, (Operand) right);
            }
            if (sign == '/') {
                left = new CheckedDivide((Operand) left, (Operand) right);
            }
        }
        return left;
    }

    private TripleExpression parseBinary(char operation) throws ParseException {
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
                if (between('0', '9')) {
                    throw new ParseException("Ожидался пробел после set");
                }
                operand2 = parseFirst();
                operand1 = new Set((Operand) operand1, (Operand) operand2);
            }
            if (op == 'c') {
                expect("lear");
                if (between('0', '9')) {
                    throw new ParseException("Ожидался пробел после clear");
                }
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

    private TripleExpression parseConst() throws WrongConstException {
        StringBuilder value = readAllNumber();
        try {
            return new Const(Integer.parseInt(value.toString()));
        } catch (NumberFormatException e) {
            throw new WrongConstException("переполнение");
        }
    }

    private TripleExpression parseNegativeConst() throws WrongConstException {
        StringBuilder value = readAllNumber();
        value.insert(0, '-');
        try {
            return new Const(Integer.parseInt(value.toString()));
        } catch (NumberFormatException e) {
            throw new WrongConstException("переполнение");
        }
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

    private StringBuilder readAllWord() {
        final StringBuilder word = new StringBuilder();
        skipWhiteSpace();
        while (Character.isLetter(ch)) {
            word.append(ch);
            next();
        }
        return word;
    }

//    private int getPos(){
//        return this.source.getPos();
//    }

    private Variable parseVariable() throws ParseException {
        final StringBuilder sb = new StringBuilder();
        while (Character.isLetter(ch)) {
            sb.append(ch);
            next();
        }
        String variable = sb.toString();
        if (variable.equals("x") || variable.equals("y") || variable.equals("z")) {
            return new Variable(variable);
        } else {
            throw new WrongVariableException("про переменную тут");
        }
    }
}






