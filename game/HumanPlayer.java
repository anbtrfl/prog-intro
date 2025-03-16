package game;

import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {

    private final Scanner in;

    public HumanPlayer() {
        this.in = new Scanner(System.in);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            System.out.println("Position");
            System.out.println(position);
            System.out.println(cell + "'s move");
            System.out.println("Enter row and column");
            String[] str = in.nextLine().split(" ");
            if (str.length != 2) {
                throw new NumberFormatException("Ошибка игрока, вы проиграли, нужно ввести ДВА числа");
            }
            int x;
            int y;
            try {
                x = Integer.parseInt(str[0]);
                y = Integer.parseInt(str[1]);
            } catch (NumberFormatException ex) {
                throw new NumberFormatException("ошибка игрока, вы проиграли, нужно вводить ЧИСЛА");
//                System.out.println("еще попытка");
//                continue;
            }
            final Move move = new Move(x, y, cell);
            if (position.isValid(move)) {
                return move;
            } else {
                throw new NumberFormatException("вы проиграли. Ошибка игрока");
            }
        }
    }
}
