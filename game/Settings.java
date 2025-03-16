package game;

public class Settings {

	private final int rows;
	private final int columns;
	private final int forWins;

	public Settings(int rows, int columns, int forWins) {
		if (rows <= 0 || columns <= 0 || forWins <= 0) {
			throw new NumberFormatException();
		}
		this.rows = rows;
		this.columns = columns;
		this.forWins = forWins;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getForWins() {
		return forWins;
	}
}
