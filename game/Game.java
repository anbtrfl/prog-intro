package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {

	private final Player player1, player2;

	public Game(final Player player1, final Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public int play(Board board) {
		while (true) {
			final int result1 = playGame(board, player1, 1);
			if (result1 != -1) {
				return result1;
			}
			final int result2 = playGame(board, player2, 2);
			if (result2 != -1) {
				return result2;
			}
		}
	}

	private int playGame(final Board board, final Player player, final int no) {
		Move move;
		try {
			move = player.move(board.getPosition(), board.getCell());
		} catch (NumberFormatException ex) {
			System.out.println(ex.getMessage());
			return 3 - no;
		}
		final Result result = board.makeMove(move);
		if (result == Result.WIN) {
			return no;
		} else if (result == Result.LOSE) {
			return 3 - no;
		} else if (result == Result.DRAW) {
			return 0;
		} else {
			return -1;
		}
	}

}
