//package expression;
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println(new Const(0).toMiniString());
//
//    }
//}
package expression;

import expression.exceptions.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionParser expressionParser = new ExpressionParser();
        String input = scanner.nextLine();
        try {
            System.out.println(expressionParser.parse(input).evaluate(1, 2, 3));
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}