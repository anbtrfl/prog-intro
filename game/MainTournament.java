package game;

import java.util.Arrays;
import java.util.Scanner;

public class MainTournament {

    public static void main(String[] args) {
        System.out.println("Введите M, N и K через пробел:");
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        if (line.length != 3) {
            System.out.println("Неверные параметры, нужно ввести 3 аргумента");
            return;
        }
        Settings settings = new Settings(3, 3, 3);
        try {
            int m = Integer.parseInt(line[0]);
            int n = Integer.parseInt(line[1]);
            int k = Integer.parseInt(line[2]);
            settings = new Settings(m, n, k);
        } catch (NumberFormatException ex) {
            System.out.println("неверные параметры mnk.поле 3*3, для победы 3 подряд.");
        }
        System.out.println("Сколько вас ??");
        int amountPeople = 2;
        try {
            amountPeople = Integer.parseInt(scanner.nextLine());
            if (amountPeople < 2) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            System.out.println("неверный параметр.вас двое");
            amountPeople = 2;
        }

        TournamentTable tournamentTable = new TournamentTable(amountPeople);
        int[] pair = tournamentTable.getNextPair();
        while (!Arrays.equals(pair, new int[]{1, 1})) {
            System.out.printf("Играют: X - Игрок #%d, О - Игрок #%d%n", pair[0] + 1, pair[1] + 1);
            final Game game = new Game(new HumanPlayer(), new HumanPlayer());
            int result = game.play(new MNKWithTabooBoard(settings));
            if (result == 1) {
                System.out.println("X Win!");
            } else if (result == 2) {
                System.out.println("O Win!");
            } else {
                System.out.println("Ничья!!!");
            }
            tournamentTable.writeWin(result);
            System.out.println(tournamentTable.showResultTable());
            pair = tournamentTable.getNextPair();
        }

    }
}
